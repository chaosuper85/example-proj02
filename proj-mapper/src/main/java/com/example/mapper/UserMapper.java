package com.example.mapper;

import com.example.pojo.TestUser;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * @author zhuchao
 * @date 2022/2/2 7:50 下午
 */

@Mapper
public interface UserMapper extends BaseMapper<TestUser> {


}