package net.xdclass.service.impl;

import net.xdclass.config.WeChatConfig;
import net.xdclass.domain.User;
import net.xdclass.mapper.UserMapper;
import net.xdclass.service.UserService;
import net.xdclass.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

/**
 * @author 容
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User saveWeChatUser(String code)  {

        String accessTokenUrl = String.format(WeChatConfig.getOpenAccessTokenUrl(),weChatConfig.getOpenAppid(),weChatConfig.getOpenRedirectUrl(),code);

        //获取access_token
        Map<String, Object> baseMap = HttpUtils.doGet(accessTokenUrl);
        if(baseMap == null || baseMap.isEmpty()){
            return null;
        }
        String accessToken = (String) baseMap.get("access_token");
        String openId = (String) baseMap.get("openid");

        User dbUser = userMapper.findByopenId(openId);
        if(dbUser != null){  //更新用户，直接返回
            return dbUser;
        }

        //获取用户基本信息
        String userInfoUrl = String.format(WeChatConfig.getOpenUserInfoUrl(),accessToken,openId);

        //获取access_token
        Map<String, Object> baseUserMap = HttpUtils.doGet(userInfoUrl);
        if(baseUserMap == null || baseUserMap.isEmpty()){
            return null;
        }
        String nickname = (String) baseUserMap.get("nickname");

        Double sexTemp = (Double) baseUserMap.get("sex");
        int sex = sexTemp.intValue();
        String province = (String) baseUserMap.get("province");
        String city = (String) baseUserMap.get("city");
        String country = (String) baseUserMap.get("country");
        String headimgurl = (String) baseUserMap.get("headimgurl");
        StringBuilder stringBuilder = new StringBuilder(country).append("||").append(province).append("||").append(city);
        String finalAddress = stringBuilder.toString();

        try {
            //解决乱码
            nickname = new String(nickname.getBytes("ISO-8859-1"),"UTF-8");
            finalAddress = new String(finalAddress.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setName(nickname);
        user.setHeadImg(headimgurl);
        user.setCity(finalAddress);
        user.setOpenid(openId);
        user.setSex(sex);
        user.setCreateTime(new Date());

        userMapper.save(user);
        return user;
    }
}
