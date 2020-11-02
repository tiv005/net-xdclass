package net.xdclass.service.impl;

import net.xdclass.domain.Video;
import net.xdclass.mapper.VideoMapper;
import net.xdclass.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 容
 * @version 1.0
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<Video> findAll() {
        return videoMapper.findAll();
    }

    @Override
    public Video findById(Integer id) {
        return videoMapper.findById(id);
    }

    @Override
    public int update(Video video) {
        return videoMapper.update(video);
    }

    @Override
    public int delete(Integer id) {
        return videoMapper.delete(id);
    }

    @Override
    public int save(Video video) {
        return videoMapper.save(video);
    }
}
