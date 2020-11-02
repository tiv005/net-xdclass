package net.xdclass.controller;

import net.xdclass.config.WeChatConfig;
import net.xdclass.domain.JsonData;
import net.xdclass.domain.Video;
import net.xdclass.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 专门用于测试
 * @author 容
 * @version 1.0
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "hello.world";
    }

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private VideoMapper videoMapper;


    @RequestMapping("/testConfig")
    public JsonData testConfig(){
        System.out.println("公众号id：" + weChatConfig.getAppId() +"\t 公众号秘钥： " + weChatConfig.getAppsecret());
        return JsonData.buildSuccess(weChatConfig.getAppId());
    }

    /**
     * 查询
     * @return
     */
    @RequestMapping("/testFindAll")
    public List<Video> testFindAll(){
        return videoMapper.findAll();
    }

    /**
     * 单一查询
     * @return
     */
    @RequestMapping("/findById")
    public Video findById(Integer id){
        return videoMapper.findById(id);
    }

    /**
     * 改
     * @return
     */
    @RequestMapping("/update")
    public int update(Video video){
        return videoMapper.update(video);
    }

    /**
     * 查
     * @return
     */
    @RequestMapping("/delete")
    public int delete(Integer id){
        return videoMapper.delete(id);
    }
}
