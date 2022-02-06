package com.example.common.utils;

import java.util.UUID;

/**
 * @author zhuchao
 * @date 2022/2/6 8:26 上午
 */
public class UUIDUtil {
    public static String getUUID(){
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        return s.replace("-", "");
    }
}
