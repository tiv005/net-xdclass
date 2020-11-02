package net.xdclass.mapper;

import net.xdclass.domain.Video;
import net.xdclass.provider.VideoProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * video数据访问层，不用注解@Service，因为在主程序使用mapperscan
 * @author 容
 * @version 1.0
 */
public interface VideoMapper {

    @Select("select * from video")
    //在mybatis没有配置情况下，解决驼峰命名查询不出数据
//    @Results({
//            @Result(column = "cover_img",property = "coverImg"),
//            @Result(column = "create_time",property = "createTime")
//    })
    List<Video> findAll();

    @Select("select * from video where id = #{id} ")
    Video findById(Integer id);

    //@Update("update video set title = #{title} where id = #{id} ")
    @UpdateProvider(type = VideoProvider.class,method = "updateVideo")
    int update(Video video);

    @Delete("delete from video where  id = #{id}")
    int delete(Integer id);

    @Insert("INSERT INTO `education_website`.`video`" +
            "(`title`, `summary`, `cover_img`, `view_num`, `price`, `create_time`, `online`, `point`) " +
            "VALUES " +
            "(#{title}, #{summary}, #{coverImg}, #{viewNum}, #{price}, #{createTime}, #{online}, #{point});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")  //获取自增id
    int save(Video video);
}
