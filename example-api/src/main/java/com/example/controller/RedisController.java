package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuchao
 * @date 2022/2/1 9:14 下午
 */
@RestController
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/redis/get")
    public Object get(String key) {
        return this.redisTemplate.opsForValue().get(key);
    }

    @GetMapping("/redis/set")
    public Object set(String key, String value) {
        this.redisTemplate.opsForValue().set(key, value);
        return 0;
    }

    @GetMapping("/redis/delete")
    public Object delete(String key) {
        this.redisTemplate.delete(key);
        return 0;
    }

}
