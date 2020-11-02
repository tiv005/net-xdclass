package net.xdclass.service;

import net.xdclass.domain.Video;
import net.xdclass.mapper.VideoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 容
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoServiceTest {

    @Autowired
    private VideoService videoService;

    @Autowired
    private VideoMapper videoMapper;

    @Test
    public void findAll() {
        List<Video> list = videoService.findAll();
        assertNotNull(list);
        for (Video video : list) {
            System.out.println(video);
        }
    }

    @Test
    public void findById() {
        Video video = videoService.findById(1);
        System.out.println(video);
        assertNotNull(video);
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void save() {
    }
}