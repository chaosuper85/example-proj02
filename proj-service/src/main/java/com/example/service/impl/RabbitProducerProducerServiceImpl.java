package com.example.service.impl;

import com.example.service.RabbitProducerService;
import java.util.Map;

import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * @author zhuchao
 * @date 2022/2/3 10:11 上午
 */
@Service
public class RabbitProducerProducerServiceImpl implements RabbitProducerService {
    /*
    private RabbitTemplate rabbitTemplate;

    final ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback(){
        @Override
        public void confirm(CorrelationData correlationData, boolean b, String s) {

        }
    };

    @Override
    public Object send(String msg, Map<String, Object> header) {
        MessageHeaders messageHeaders = new MessageHeaders(header);
        Message message = MessageBuilder.createMessage(msg, messageHeaders);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public org.springframework.amqp.core.Message postProcessMessage(
                    org.springframework.amqp.core.Message message) throws AmqpException {
                return message;
            }
        };

        rabbitTemplate.convertAndSend(
                "exchange-1",
                "springboot.rabbit",
                message,
                messagePostProcessor,
                correlationData);
        return "ok";

    }

     */


}
