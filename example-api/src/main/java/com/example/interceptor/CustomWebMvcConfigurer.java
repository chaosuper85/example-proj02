package com.example.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhuchao
 * @date 2022/2/9 11:29 上午
 */
@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new OneInterceptor()).addPathPatterns("/*");
        registry.addInterceptor(new TwoInterceptor()).addPathPatterns("/*");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
