package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuchao
 * @date 2022/2/9 9:06 上午
 */
@RestController
public class LoginController {

    @GetMapping("/index")
    public Object index() {
        return "index";
    }

    @GetMapping("/needlogin")
    public Object needlogin() {
        return "needlogin";
    }

    @GetMapping("/login")
    public Object login() {
        return "login";
    }
}
