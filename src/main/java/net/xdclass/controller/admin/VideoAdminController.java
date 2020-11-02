package net.xdclass.controller.admin;

import net.xdclass.domain.Video;
import net.xdclass.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员操作
 * @author 容
 * @version 1.0
 */
@RestController
@RequestMapping("/admin/api/v1/video/")
public class VideoAdminController {


    @Autowired
    private VideoService videoService;


    /**
     * 根据id更新视频
     * @param
     * @param
     * @return
     */
    @PutMapping("update_by_id")
    public int update(@RequestBody Video video){

        return  videoService.update(video);
    }

    /**
     * 根据id删除视频
     * @param videoId
     * @return
     */
    @DeleteMapping("del_by_id")
    public int delById(@RequestParam(value = "video_id",required = true)Integer videoId){
        return  videoService.delete(videoId);
    }

    /**
     * 保存视频对象
     * @param
     * @return
     */
    @PostMapping("save")
    public int save(@RequestBody Video video){
        return  videoService.save(video);
    }
}
