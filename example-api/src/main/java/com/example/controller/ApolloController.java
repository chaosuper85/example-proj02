package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuchao
 * @date 2022/2/4 5:10 下午
 */
@RestController
public class ApolloController {
    //@Value("${data.value}")
    String dataValue;

    @GetMapping("/config")
    public String config() {
        return "打印配置中心的 dataValue 值: "+ dataValue;
    }

}
