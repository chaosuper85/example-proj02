package com.example.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhuchao
 * @date 2022/2/9 2:50 下午
 */
@Configuration
public class BeanExampleConfig {
    @Bean
    public ConfigBean configBean() {
        return new ConfigBean();
    }
}
