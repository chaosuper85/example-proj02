package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author zhuchao
 * @date 2022/2/6 9:09 下午
 */
//@SpringBootConfiguration
//@PropertySource(value = "classpath:/redis.properties")
public class RedisConfiguration {
/*
    @Value("${redis.node.maxtotal}")
    private Integer maxTotal;
    @Value("${redis.node.host}")
    private String host;
    @Value("${redis.node.port}")
    private Integer port;
    @Value("${redis.node.password}")
    private String password;
    @Value("${redis.node.timeout}")
    private Integer timeout;

    public JedisPoolConfig jedisPoolConfig() {    //这个是修改redis性能的时候需要的对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        return jedisPoolConfig;
    }

    @Bean  //这个注解注入工厂的名称是方法名
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = jedisPoolConfig();
        return new JedisPool(jedisPoolConfig, host, port, timeout, password);
    }

 */

}
