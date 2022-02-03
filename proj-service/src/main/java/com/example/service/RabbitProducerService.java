package com.example.service;

import java.util.Map;

/**
 * @author zhuchao
 * @date 2022/2/3 10:11 上午
 */
public interface RabbitProducerService {
    Object send(String msg, Map<String, Object> header);
}
