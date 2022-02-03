package com.example.service.impl;

import com.example.mapper.UserMapper;
import com.example.pojo.TestUser;
import com.example.service.UserService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author zhuchao
 * @date 2022/2/2 7:51 下午
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Integer addUser(TestUser user) {
        return userMapper.insert(user);
    }

    @Override
    public List<TestUser> getUsers() {
        return userMapper.selectAll();
    }

    @Override
    public TestUser getUser(Integer id) {
        TestUser testUser = new TestUser();
        testUser.setId(Long.valueOf(id));
        return userMapper.selectOne(testUser);
    }

    @Override
    public boolean deleteOne(Integer id) {
        TestUser testUser = new TestUser();
        testUser.setId(Long.valueOf(id));
        int i = userMapper.delete(testUser);
        return i > 0;
    }

}

