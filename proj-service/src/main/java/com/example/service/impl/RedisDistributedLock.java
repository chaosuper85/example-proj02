package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

/**
 * @author zhuchao
 * @date 2022/2/6 6:30 下午
 */
@Service
public class RedisDistributedLock extends AbstractDistributedLock {

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 获取锁
     *
     * @param lockKey    锁
     * @param identity   身份标识（保证锁不会被其他人释放）
     * @param expireTime 锁的过期时间（单位：秒）
     * @return
     */
    @Override
    public boolean lock(String lockKey, String identity, long expireTime) {
        return  redisTemplate.opsForValue().setIfAbsent(lockKey, identity, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 释放锁
     *
     * @param lockKey  锁
     * @param identity 身份标识（保证锁不会被其他人释放）
     * @return
     */
    @Override
    public boolean releaseLock(String lockKey, String identity) {
        String luaScript = "if " +
                "  redis.call('get', KEYS[1]) == ARGV[1] " +
                "then " +
                "  return redis.call('del', KEYS[1]) " +
                "else " +
                "  return 0 " +
                "end";
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Boolean.class);
        redisScript.setScriptText(luaScript);
        List<String> keys = new ArrayList<>();
        keys.add(lockKey);
        Object result = redisTemplate.execute(redisScript, keys, identity);
        return (boolean) result;
    }

}
