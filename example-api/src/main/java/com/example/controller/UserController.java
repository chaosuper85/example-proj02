package com.example.controller;

import com.example.pojo.TestUser;
import com.example.service.UserService;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuchao
 * @date 2022/2/2 7:52 下午
 */
@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/getUsers")
    public Object getUsers() {
        List<TestUser> list = userService.getUsers();
        Collections.sort(list);
        return list;
    }

    @GetMapping("/getUser")
    public Object getUser(@RequestParam Integer id) {
        return userService.getUser(id);
    }

    @GetMapping("/addUsers")
    public Object add() {
        for (int i = 1; i <= 5; i++) {
            TestUser user = new TestUser();
            user.setId(Long.valueOf(i));
            user.setUsername("sharding-" + (i));
            user.setPassword("pw" + i);
            long resutl = userService.addUser(user);
            logger.info("insert:" + user.toString() + " result:" + resutl);
        }
        return "添加成功";
    }

    @GetMapping("/deleteOne")
    public Object deleteOne(@RequestParam Integer id) {
        userService.deleteOne(id);
        return "删除成功！";
    }
}