package com.example.controller;

import com.example.conditional.People;
import com.example.conditional.SystemBean;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuchao
 * @date 2022/2/9 3:08 下午
 */
@RestController
public class ConditionalController {
    @Autowired
    private SystemBean windowsBean;
    @Autowired
    private SystemBean macBean;

    @GetMapping("/cond/test")
    public Object test() {
        if(windowsBean!=null) {
            System.out.println("sysname="+windowsBean.getSystemName());
        }
        if(macBean!=null) {
            System.out.println("sysname="+macBean);
        }
        return "ok";
    }
}
