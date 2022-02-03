package com.example.service.impl;

import com.example.service.RabbitConsumerService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author zhuchao
 * @date 2022/2/3 4:12 下午
 */
@Component
public class RabbitConsumerServiceImpl implements RabbitConsumerService {
/*
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queue-1", durable = "true"),
            exchange = @Exchange(name = "exchange-1", durable = "true", type = "topic", ignoreDeclarationExceptions = "true"),
            key = "springboot.*"
        )
    )
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception {
        //收到消息后进行业务端消费处理
        System.out.println("message"+message.getPayload());

        //处理成功后进行ack操作
        channel.basicAck((long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG), false);
    }

 */
}
