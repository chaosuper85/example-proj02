package com.example.test;

import com.example.ExampleApplication;
import com.example.service.impl.RedisDistributedLock;
import java.util.UUID;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhuchao
 * @date 2022/2/6 7:31 下午
 */
@RunWith(SpringRunner.class)  //底层用junit  SpringJUnit4ClassRunner
@SpringBootTest(classes={ExampleApplication.class})//启动整个springboot工程
public class RedisLockTest {
    private String lockKey = "lockKey";
    private String identity = UUID.randomUUID().toString();

    @Autowired
    private RedisDistributedLock redisDistributedLock;

    @Test
    public void testOne(){
        //String key, long expire, int retryTimes, long sleepMillis
        long expireTime = 60L;
        boolean bLockRet = redisDistributedLock.lock(lockKey, identity, expireTime);
        System.out.println("lock result:" + bLockRet);
    }

    @Before
    public void testBefore(){
        System.out.println("before");
    }

    @After
    public void testAfter(){
        boolean bUnLockRet = redisDistributedLock.releaseLock(lockKey, identity);
        System.out.println("unlock result:" + bUnLockRet);
        //TestCase.assertEquals(true, bUnLockRet);
    }
}
