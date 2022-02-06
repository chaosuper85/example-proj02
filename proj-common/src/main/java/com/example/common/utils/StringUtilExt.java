package com.example.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhuchao
 * @date 2022/2/6 9:13 上午
 */
public class StringUtilExt {

    public static boolean isNotEmptyAndBlack(String str){
        return StringUtils.isNotBlank(str) && StringUtils.isNotEmpty(str);
    }
}
