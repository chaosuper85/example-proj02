package com.example.controller;

import com.example.annotation.MyLog;
import com.example.annotation.ResultRecorder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuchao
 * @date 2022/1/29 2:18 下午
 */

@RestController
@ResultRecorder(value = "HelloControllerName")
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);


    @MyLog(value = "test log annotation")
    @GetMapping("/test")
    public Object test() {
        logger.info("hello");
        return "ok";

    }

}
