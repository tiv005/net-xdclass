package net.xdclass.service.impl;

import net.xdclass.config.WeChatConfig;
import net.xdclass.domain.User;
import net.xdclass.domain.Video;
import net.xdclass.domain.VideoOrder;
import net.xdclass.dto.VideoOrderDto;
import net.xdclass.mapper.UserMapper;
import net.xdclass.mapper.VideoMapper;
import net.xdclass.mapper.VideoOrderMapper;
import net.xdclass.service.VideoOrderService;
import net.xdclass.utils.CommonUtils;
import net.xdclass.utils.HttpUtils;
import net.xdclass.utils.WXPayUtil;
import net.xdclass.utils.WXPayXmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author 容
 * @version 1.0
 */
@Service
public class VideoOrderServiceImpl implements VideoOrderService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Logger dataLogger = LoggerFactory.getLogger("dataLogger");

    @Autowired
    private VideoOrderMapper videoOrderMapper;
    
    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WeChatConfig weChatConfig;
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String save(VideoOrderDto videoOrderDto) throws Exception {

        dataLogger.info("module=video_order `api=save`user_id={}",videoOrderDto.getUserId());

        //查找视频信息
        Video video = videoMapper.findById(videoOrderDto.getVideoId());

        //查找用户信息
        User user = userMapper.findById(videoOrderDto.getUserId());

        //生成订单
        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setTotalFee(video.getPrice());
        videoOrder.setVideoImg(video.getCoverImg());
        videoOrder.setVideoTitle(video.getTitle());
        videoOrder.setVideoId(video.getId());
        videoOrder.setCreateTime(new Date());

        videoOrder.setState(0);
        videoOrder.setUserId(user.getId());
        videoOrder.setHeadImg(user.getHeadImg());
        videoOrder.setNickname(user.getName());

        videoOrder.setDel(0);
        videoOrder.setIp(videoOrderDto.getIp());
        videoOrder.setOutTradeNo(CommonUtils.generateUUID());
        videoOrderMapper.insert(videoOrder);


        //获取codeUrl
        String codeUrl = unifiedOrder(videoOrder);



        return codeUrl;
    }

    /**
     * 根据交易订单号获取订单对象
     * @param outTradeNo
     * @return
     */
    @Override
    public VideoOrder findByOutTradeNo(String outTradeNo) {

        return videoOrderMapper.findByOutTradeNo(outTradeNo);
    }

    /**
     * 根据订单流水号更新订单
     * @param videoOrder
     * @return
     */
    @Override
    public int updateVideoOrderByOutTradeNo(VideoOrder videoOrder) {
        return videoOrderMapper.updateVideoOrderByOutTradeNo(videoOrder);
    }

    /**
     * 统一下单方法
     * 对照文档：https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
     * @return
     */
    private String unifiedOrder(VideoOrder videoOrder) throws Exception {

        //生成签名
        SortedMap<String,String> params = new TreeMap<>();
        params.put("appid",weChatConfig.getAppId());  //公众号id
        params.put("mch_id",weChatConfig.getMchId());  //商户号
        params.put("nonce_str",CommonUtils.generateUUID());  //随机字符串
        params.put("body",videoOrder.getVideoTitle());  //商品描述
        //商户订单号,商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一
        params.put("out_trade_no",videoOrder.getOutTradeNo());
        params.put("total_fee",videoOrder.getTotalFee().toString());  //标价金额分
        params.put("spbill_create_ip",videoOrder.getIp());
        params.put("notify_url",weChatConfig.getPayCallBackUrl());  //通知地址
        params.put("trade_type","NATIVE");  //交易类型 JSAPI 公众号支付 NATIVE扫码支付APP APP支付

        //sign签名
        String sign = WXPayUtil.createSign(params, weChatConfig.getKey());
        params.put("sign",sign);

        //map转xml
        String payXml = WXPayUtil.mapToXml(params);
        System.out.println(payXml);

        //统一下单
        String orderStr = HttpUtils.doPost(WeChatConfig.getUnifiedOrderUrl(), payXml, 4000);
        if(orderStr == null){
            return null;
        }

        Map<String, String> unifiedOrderMap = WXPayUtil.xmlToMap(orderStr);
        System.out.println(unifiedOrderMap.toString());
        if(unifiedOrderMap != null){
            return unifiedOrderMap.get("code_url");
        }
        return null;
    }
}
