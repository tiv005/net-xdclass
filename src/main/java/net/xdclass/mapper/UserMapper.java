package net.xdclass.mapper;

import net.xdclass.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author 容
 * @version 1.0
 */
public interface UserMapper {

    /**
     * 根据主键id查找
     * @param userId
     * @return
     */
    @Select("select * from user where id = #{id}")
    User findById(@Param("id") int userId);

    /**
     * 根据openId找用户
     * @param openId
     * @return
     */
    @Select("select * from user where openid = #{openid}")
    User findByopenId(@Param("openid") String openId);

    @Insert("INSERT INTO `education_website`.`user`(`openid`, `name`, `head_img`, `phone`, `sign`, `sex`, `city`, `create_time`)" +
            " VALUES " +
            "(#{openid}, #{name}, #{headImg}, #{phone}, #{sign}, #{sex}, #{city}, #{createTime});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int save(User user);
}
