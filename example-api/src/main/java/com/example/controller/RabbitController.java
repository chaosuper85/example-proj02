package com.example.controller;

import com.example.service.RabbitProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuchao
 * @date 2022/2/3 10:13 上午
 */
@RestController
public class RabbitController {
    @Autowired
    private RabbitProducerService rabbitProducerService;

    @GetMapping("/send/msg")
    public Object send(String msg){
        return rabbitProducerService.send(msg, null);
    }
}
