package com.example.controller;

import com.example.conditional.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuchao
 * @date 2022/2/9 4:56 下午
 */
@RestController
public class ConditionalBeanController {
    @Autowired(required=false)
    private People people;
    @GetMapping("/test1")
    public String test1() {
        System.out.println("people:"+people);
        return "ok";
    }
}
