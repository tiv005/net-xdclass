package net.xdclass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("net.xdclass.mapper")
@EnableTransactionManagement //开启事务管理
public class NetXdclassApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetXdclassApplication.class, args);
    }
}
