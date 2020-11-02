package net.xdclass.service;

import net.xdclass.domain.VideoOrder;
import net.xdclass.dto.VideoOrderDto;
import org.apache.ibatis.annotations.Param;

/**
 * 订单接口
 * @author 容
 * @version 1.0
 */
public interface VideoOrderService {

    /**
     * 下单接口
     * @param videoOrderDto
     * @return
     */
    String save(VideoOrderDto videoOrderDto) throws Exception;

    /**
     * 根据交易订单号获取订单对象
     */
    VideoOrder findByOutTradeNo(String outTradeNo);

    /**
     * 根据订单流水号更新订单
     */
    int updateVideoOrderByOutTradeNo(VideoOrder videoOrder);
}
