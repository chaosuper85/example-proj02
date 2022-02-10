package com.example.controller;

import com.example.async.AsyncTask;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuchao
 * @date 2022/2/9 1:44 下午
 */
@RestController
public class AsyncController {


    @Autowired
    private AsyncTask asyncTask;

    @GetMapping("/async/test")
    public Object test() throws InterruptedException {
        long b = System.currentTimeMillis();
        Future<String> stringFuture1 = asyncTask.task1();
        Future<String> stringFuture2 = asyncTask.task2();
        Future<String> stringFuture3 = asyncTask.task3();

        while (true) {
            if(stringFuture1.isDone()&&stringFuture2.isDone()&&stringFuture3.isDone()) {
                break;
            }
        }
        long e = System.currentTimeMillis();
        System.out.println("total cost:" + (e-b)/1000);

        return "ok";
    }

}
