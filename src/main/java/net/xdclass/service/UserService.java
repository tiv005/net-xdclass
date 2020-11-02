package net.xdclass.service;

import net.xdclass.domain.User;

import java.io.UnsupportedEncodingException;

/**
 * 用户业务接口类
 * @author 容
 * @version 1.0
 */
public interface UserService {

    public User saveWeChatUser(String code) throws UnsupportedEncodingException;
}
