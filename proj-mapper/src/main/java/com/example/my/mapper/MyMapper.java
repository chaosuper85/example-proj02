package com.example.my.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author zhuchao
 * @date 2022/1/29 11:47 下午
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}

