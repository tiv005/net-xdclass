package net.xdclass.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 常用工具类的封装，md5、uuid等
 * @author 容
 * @version 1.0
 */
public class CommonUtils {

    /**
     * 生成uuid，即用来标识一笔单，也用做nonce_str
     * @return
     */
    public static String generateUUID(){

        String uuid = UUID.randomUUID()
                .toString()
                .replaceAll("-", "")
                .substring(0, 32);
        return uuid;
    }

    /**
     * md5常用工具类
     * @param data
     * @return
     */
    public static String MD5(String data)  {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] array = new byte[0];
        try {
            array = md5.digest(data.getBytes("UTF-8"));
            StringBuilder builder = new StringBuilder();
            for (byte item : array) {
                builder.append(Integer.toHexString((item & 0xFF) | 0x100 ).substring(1,3));
            }
            return builder.toString().toUpperCase();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
       return null;
    }
}
