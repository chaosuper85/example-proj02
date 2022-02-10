package com.example.conditional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhuchao
 * @date 2022/2/9 4:18 下午
 */
@Slf4j
@Configuration
public class BeanConfig {
    @Bean
    public City city() {
        City city = new City();
        city.setCityName("千岛湖");
        return city;
    }
    @Bean
    @ConditionalOnBean(name = "city")
    public People people(City city) {
        //这里如果city实体没有成功注入 这里就会报空指针
        city.setCityCode(301701);
        return new People("小小", 3, city);
    }
}