package com.example.controller;

import com.example.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuchao
 * @date 2022/2/3 6:25 下午
 */
@RestController
public class KafkaController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @GetMapping("/sendMessage")
    public String sendMessage(String msg) {
        kafkaProducerService.send(msg);
        return "ok";
    }
}
