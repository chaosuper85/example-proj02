package com.example.kafkamsg;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author zhuchao
 * @date 2022/2/3 6:27 下午
 */
@Component
@Slf4j
public class KafkaConsumer {

    /**
     * 本地kafka没有启动起来的情况下，会报错
     * 临时注释掉这个注解：KafkaListener
     * @param record
     */
    // @KafkaListener(topics = {"hello2"})
    public void listen(ConsumerRecord<?, ?> record) {

        Optional.ofNullable(record.value())
                .ifPresent(message -> {
                    log.info("【+++++++++++++++++ record = {} 】", record);
                    log.info("【+++++++++++++++++ message = {}】", message);
                });
        /*
        【+++++++++++++++++ record = ConsumerRecord(topic = hello2, partition = 0, offset = 0, CreateTime = 1643884191119, serialized key size = -1, serialized value size = 75, headers = RecordHeaders(headers = [], isReadOnly = false), key = null, value = {"id":1643884188767,"msg":"helloworld","sendTime":"Feb 3, 2022 6:29:49 PM"}) 】
         */
        /*
        【+++++++++++++++++ message = {"id":1643884188767,"msg":"helloworld","sendTime":"Feb 3, 2022 6:29:49 PM"}】
         */
    }

}
