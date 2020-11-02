package net.xdclass.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 跨域处理
 * WebMvcConfigurerAdapter  已过期
 * Spring 5.0后，WebMvcConfigurerAdapter被废弃，取代的方法有两种：
 * ①implements WebMvcConfigurer（官方推荐）
 * ②extends WebMvcConfigurationSupport
 */
@Configuration
public class Cross extends WebMvcConfigurationSupport {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","POST","OPTIONS","DELETE","PATCH")
                .allowCredentials(true).maxAge(3600);
    }
}
