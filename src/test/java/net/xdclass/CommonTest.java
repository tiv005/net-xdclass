package net.xdclass;

import io.jsonwebtoken.Claims;
import net.xdclass.domain.User;
import net.xdclass.utils.JwtUtils;
import org.junit.Test;

/**
 * 测试jwt
 * @author 容
 * @version 1.0
 */
public class CommonTest {

    /**
     * 加密
     */
    @Test
    public void testGeneJwt(){
        User user = new User();
        user.setId(999);
        user.setHeadImg("www.xdclass.net");
        user.setName("xd");

        String token  = JwtUtils.geneJsonWebToken(user);
        System.out.println(token);
    }

    /**
     * 解密
     */
    @Test
    public void testCheck(){

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ4c2NsYXNzIiwiaWQiOjk5OSwibmFtZSI6InhkIiwiaW1nIjoid3d3LnhkY2xhc3MubmV0IiwiaWF0IjoxNjAzMzQ5NTcxLCJleHAiOjE2MDM5NTQzNzF9.lyDpRmTHWrXeAHwWubE9dtV8eb47qA9adoC-TABVZz0";
        Claims claims = JwtUtils.checkJWT(token);
        if (claims != null){
            String name = (String) claims.get("name");
            String img = (String) claims.get("img");
            int id = (Integer) claims.get("id");
            System.out.println(name);
            System.out.println(img);
            System.out.println(id);
        }else {
            System.out.println("非法token");
        }
    }
}
