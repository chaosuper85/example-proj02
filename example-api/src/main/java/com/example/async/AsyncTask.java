package com.example.async;

import java.util.concurrent.Future;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

/**
 * @author zhuchao
 * @date 2022/2/9 1:44 下午
 */
@Component
@Async
public class AsyncTask {

    public Future<String> task1() throws InterruptedException {
        long b = System.currentTimeMillis();
        Thread.sleep(3000L);
        long e = System.currentTimeMillis();
        System.out.println("task1 cost:" + (e-b)/1000);
        return new AsyncResult<>("task1 return ok");
    }

    public Future<String> task2() throws InterruptedException {
        long b = System.currentTimeMillis();
        Thread.sleep(4000L);
        long e = System.currentTimeMillis();
        System.out.println("task2 cost:" + (e-b)/1000);
        return new AsyncResult<>("task2 return ok");
    }

    public Future<String> task3() throws InterruptedException {
        long b = System.currentTimeMillis();
        Thread.sleep(5000L);
        long e = System.currentTimeMillis();
        System.out.println("task3 cost:" + (e-b)/1000);
        return new AsyncResult<>("task3 return ok");
    }


}
