package com.example.controller;

import com.example.common.JSONResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuchao
 * @date 2022/2/4 10:52 下午
 */
@RestController
public class ExceptionController {
    @GetMapping("/getById/{userId}")
    public JSONResult getById(@PathVariable Integer userId) throws Exception {
        // 手动抛出异常
        if(true) {
            throw new Exception("抛出异常了");
        }

        //int a = 10/0;
        return JSONResult.ok();
    }

}
