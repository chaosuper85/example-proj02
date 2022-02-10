package com.example.controller;

import com.example.validate.TestDTO;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuchao
 * @date 2022/2/9 7:01 下午
 */
@RestController
public class ValidateTestController {
    @PostMapping("/validate/test/")
    public Object test(@Validated @RequestBody TestDTO testDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return bindingResult.toString();
        }
        return "ok";
    }
}
