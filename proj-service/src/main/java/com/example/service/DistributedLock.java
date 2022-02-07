package com.example.service;

/**
 * @author zhuchao
 * @date 2022/2/6 6:29 下午
 */
public interface DistributedLock {
    public static final long TIMEOUT_MILLIS = 30000;

    public static final int RETRY_TIMES = Integer.MAX_VALUE;

    public static final long SLEEP_MILLIS = 500;


    public boolean lock(String lockKey, String identity, long expireTime);

    public boolean releaseLock(String lockKey, String identity);

    /*
    public boolean lock(String key);

    public boolean lock(String key, int retryTimes);

    public boolean lock(String key, int retryTimes, long sleepMillis);

    public boolean lock(String key, long expire);

    public boolean lock(String key, long expire, int retryTimes);

    public boolean lock(String key, long expire, int retryTimes, long sleepMillis);

    public boolean releaseLock(String key);

     */

}
