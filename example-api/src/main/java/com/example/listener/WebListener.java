package com.example.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * @author zhuchao
 * @date 2022/2/9 11:10 上午
 */
@javax.servlet.annotation.WebListener
public class WebListener implements ServletRequestListener {

    @Override
    public  void requestDestroyed (ServletRequestEvent sre) {
        System.out.println("enter requestDestroyed...");
    }

    @Override
    public  void requestInitialized (ServletRequestEvent sre) {
        System.out.println("enter requestInitialized...");
    }
}
