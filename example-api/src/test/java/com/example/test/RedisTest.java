package com.example.test;

import com.example.ExampleApplication;
import com.example.service.impl.RedisDistributedLock;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author zhuchao
 * @date 2022/2/6 7:31 下午
 */
@RunWith(SpringRunner.class)  //底层用junit  SpringJUnit4ClassRunner
@SpringBootTest(classes={ExampleApplication.class})//启动整个springboot工程
public class RedisTest {

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void testOne(){
        Jedis jedis = jedisPool.getResource();
        jedis.set("testk", "testv");
        String res = jedis.get("testk");
        System.out.println("res:" + res);
        TestCase.assertEquals("testv", res);
    }

    @Before
    public void testBefore(){
        System.out.println("before");
    }

    @After
    public void testAfter(){
        System.out.println("after");
    }
}
