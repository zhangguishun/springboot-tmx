package com.example.uniappspringboot.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 文件上传
 */



@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        windows地址
//        registry.addResourceHandler("/img/**").addResourceLocations("file:F:\\web\\");
//        WebMvcConfigurer.super.addResourceHandlers(registry);

        registry.addResourceHandler("/img/**").addResourceLocations("file:/www/wwwroot/www.yubin-fuwu.top/Static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);//服务器地址
    }

}
