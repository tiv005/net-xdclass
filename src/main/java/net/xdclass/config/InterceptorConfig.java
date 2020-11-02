package net.xdclass.config;

import net.xdclass.interceoter.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 * @author 容
 * @version 1.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/api/v1/*/**");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
