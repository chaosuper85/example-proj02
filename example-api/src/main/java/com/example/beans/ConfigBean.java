package com.example.beans;

/**
 * @author zhuchao
 * @date 2022/2/9 2:49 下午
 */
public class ConfigBean {
    private String type = "@Configuration注解生成的bean实体";
    public String getName(String name) {
        return name + "--" + type;
    }
}
