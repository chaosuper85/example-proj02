package com.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuchao
 * @date 2022/2/8 2:40 下午
 */
@RestController
public class SentinelController {
    @GetMapping("/sentinel")
    public String sentinel() {
        return "sentinel ....";
    }
}
