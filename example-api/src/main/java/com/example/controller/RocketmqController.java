package com.example.controller;

import com.example.service.RocketmqProducerService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuchao
 * @date 2022/2/4 8:52 下午
 */


@Slf4j
@RestController
public class RocketmqController {

    @Autowired
    private RocketmqProducerService producer;

    /**
     * 初始化消息
     */
    public RocketmqController() {
    }

    @RequestMapping("/text/rocketmq")
    public Object rocketmqSend() {
        try {
            List<String> mesList = new ArrayList<>();
            mesList.add("小小");
            mesList.add("爸爸");
            mesList.add("妈妈");
            mesList.add("爷爷");
            mesList.add("奶奶");
            //总共发送五次消息
            for (String s : mesList) {
                //创建生产信息
                Message message = new Message(RocketmqProducerService.TOPIC, "testtag",
                        ("小小一家人的称谓:" + s).getBytes());
                //发送
                SendResult sendResult = producer.getProducer().send(message);
                log.info("输出生产者信息={}", sendResult);
            }
        } catch (Exception e) {
            log.error("有异常", e);
        }

        return "成功";
    }
}