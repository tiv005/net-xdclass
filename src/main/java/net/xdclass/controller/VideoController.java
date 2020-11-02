package net.xdclass.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.xdclass.domain.Video;
import net.xdclass.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author 容
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    /**
     * 分页接口
     * @param page  当前第几页，默认是第一页
     * @param size  每页显示几条
     * @return
     */
    @GetMapping("/page")
    public Object pageVideo(@RequestParam(value = "page",defaultValue = "1") int page,
                            @RequestParam(value = "size",defaultValue = "10") int size){

        PageHelper.startPage(page,size);
        List<Video> list = videoService.findAll();
        PageInfo<Video> pageInfo = new PageInfo<>(list);
        HashMap<String, Object> data = new HashMap<>();
        data.put("total_size",pageInfo.getTotal());  //总条数
        data.put("total_page",pageInfo.getPages());  //总页数
        data.put("current_page",page);     //当前页数
        data.put("data",pageInfo.getList());   //数据
        return data;
    }

    /**
     * 根据id找视频
     * @param videoId
     * @return
     */
    @GetMapping("/findById")
    public Object findById(@RequestParam(value = "video_id",required = true) Integer videoId){
        return  videoService.findById(videoId);
    }

}
