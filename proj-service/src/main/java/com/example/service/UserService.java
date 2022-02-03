package com.example.service;

import com.example.pojo.TestUser;
import java.util.List;

/**
 * @author zhuchao
 * @date 2022/2/2 7:51 下午
 */
public interface UserService {

    Integer addUser(TestUser user);

    List<TestUser> getUsers();

    TestUser getUser(Integer id);

    boolean deleteOne(Integer id);

}