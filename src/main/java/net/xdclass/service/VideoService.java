package net.xdclass.service;

import net.xdclass.domain.Video;

import java.util.List;

/**
 * @author 容
 * @version 1.0
 */
public interface VideoService {

    List<Video> findAll();

    Video findById(Integer id);

    int update(Video video);

    int delete(Integer id);

    int save(Video video);
}
