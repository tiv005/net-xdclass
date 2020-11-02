package net.xdclass.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 防止乱码
 * @author 容
 * @version 1.0
 */
@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        super.configureMessageConverters(converters);
    }
}
