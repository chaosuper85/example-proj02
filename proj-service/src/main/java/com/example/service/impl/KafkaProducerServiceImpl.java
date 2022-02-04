package com.example.service.impl;

import com.example.pojo.kafka.MessageEntity;
import com.example.service.KafkaProducerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zhuchao
 * @date 2022/2/3 6:22 下午
 */
@Component
@Slf4j
public class KafkaProducerServiceImpl implements KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    //构造器方式注入  kafkaTemplate
    public KafkaProducerServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private Gson gson = new GsonBuilder().create();

    @Override
    public void send(String msg) {
        MessageEntity message = new MessageEntity();

        message.setId(System.currentTimeMillis());
        message.setMsg(msg);
        message.setSendTime(new Date());
        log.info("【++++++++++++++++++ message ：{}】", gson.toJson(message));
        //对 topic =  hello2 的发送消息
        kafkaTemplate.send("hello2",gson.toJson(message));
    }

}
