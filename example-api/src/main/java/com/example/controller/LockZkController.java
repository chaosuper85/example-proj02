package com.example.controller;

import com.example.service.DistributeLockZK;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuchao
 * @date 2022/2/8 2:40 下午
 */
@RestController
public class LockZkController {
    @Autowired
    private DistributeLockZK distributeLockZK;

    @GetMapping("/zklock/lock")
    public Object lock(String path) {
        //1、加分布式锁
        InterProcessLock interProcessLock = distributeLockZK.acquire(path, 10, TimeUnit.SECONDS);
        boolean bLockResult = interProcessLock != null;
        System.out.println("zk lock result:" + bLockResult);

        try{
            Thread.sleep(10000L); //2、模拟当前线程的工作内容
        }catch (Exception e) {
            e.printStackTrace();
        }

        //3、释放分布式锁
        boolean bRet  = distributeLockZK.release(interProcessLock);
        System.out.println("zk unlock result:" + bRet);
        return "lock ok";
    }
}
