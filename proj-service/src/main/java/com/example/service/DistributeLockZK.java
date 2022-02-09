package com.example.service;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhuchao
 * @date 2022/2/7 8:43 下午
 */
@Service
@Slf4j
public class DistributeLockZK  {
    private final static String ROOT_PATH = "/zk_locck";
    @Autowired
    private CuratorFramework curatorFramework;

    public InterProcessLock acquire(String path, long waitTime, TimeUnit timeUnit) {
        String realPath = ROOT_PATH + "/" + path;
        InterProcessLock interProcessLock = new InterProcessMutex(curatorFramework, realPath);
        try {
            boolean acquire = interProcessLock.acquire(waitTime, timeUnit);
            if (acquire) {
                return interProcessLock;
            }
        } catch (Exception e) {
            log.error("zk加锁错误", e);
            return null;
        }
        return null;
    }

    public boolean release(InterProcessLock interProcessLock) {
        boolean acquiredInThisProcess = interProcessLock.isAcquiredInThisProcess();
        if (acquiredInThisProcess) {
            try {
                interProcessLock.release();
                System.out.println("解锁成功");
                return true;
            } catch (Exception e) {
                log.error("zk解锁错误", e);
                return false;
            }
        }
        return false;
    }
}
