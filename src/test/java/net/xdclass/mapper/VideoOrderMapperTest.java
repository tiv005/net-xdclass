package net.xdclass.mapper;

import net.xdclass.domain.VideoOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author 容
 * @version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class VideoOrderMapperTest {

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Test
    public void insert() {

        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setDel(0);
        videoOrder.setTotalFee(111);
        videoOrder.setHeadImg("daadgaeagsadgegadgvavadae");
        videoOrder.setVideoTitle("springBoot高级视频教程");
        videoOrderMapper.insert(videoOrder);
        assertNotNull(videoOrder.getId());
    }

    @Test
    public void findById() {
        VideoOrder videoOrder = videoOrderMapper.findById(6);
        System.out.println(videoOrder);
        assertNotNull(videoOrder);
    }

    @Test
    public void findByOutTradeNo() {
    }

    @Test
    public void del() {
    }

    @Test
    public void findMyOrderList() {
    }

    @Test
    public void updateVideoOrderByOutTradeNo() {
    }
}