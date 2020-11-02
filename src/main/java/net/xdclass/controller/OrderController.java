package net.xdclass.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import net.xdclass.dto.VideoOrderDto;
import net.xdclass.service.VideoOrderService;
import net.xdclass.utils.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单接口
 * @author 容
 * @version 1.0
 */
@RestController
@RequestMapping("/user/api/v1/order")
//@RequestMapping(value = "/api/v1/order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Logger dataLogger = LoggerFactory.getLogger("dataLogger");

    @Autowired
    private VideoOrderService videoOrderService;

    @GetMapping("/add")
    @CrossOrigin
    public void saveOrder(@RequestParam(value = "video_id",required = true)int videoId,
                              HttpServletRequest request,
                          HttpServletResponse response) throws Exception {
        String ip = IpUtils.getIpAddr(request);  //这个也可以临时写死，用来测试
        int userId = (int) request.getAttribute("user_id");
//        String ip = "192.168.1.102";
//        int userId = 1;  //临时写死，用来测试用
        VideoOrderDto dto = new VideoOrderDto();
        dto.setUserId(userId);
        dto.setVideoId(videoId);
        dto.setIp(ip);

        String codeUrl = videoOrderService.save(dto);
        if(codeUrl == null){
            throw new NullPointerException();
        }

        try{
            //生成二维码配置
            Map<EncodeHintType, Object> hihts = new HashMap<>();

            //设置纠错等级
            hihts.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            //编码类型
            hihts.put(EncodeHintType.CHARACTER_SET,"UTF-8");

            BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE, 400, 400, hihts);
            OutputStream outputStream = response.getOutputStream();

            MatrixToImageWriter.writeToStream(bitMatrix,"png",outputStream);
        }catch (Exception e){
            e.printStackTrace();
        }

        //return JsonData.buildSuccess("下单成功");
    }
}
