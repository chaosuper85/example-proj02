package com.example.beans;

import org.springframework.stereotype.Component;

/**
 * @author zhuchao
 * @date 2022/2/9 2:44 下午
 */
@Component("componentBean")
public class ComponentBean {

    private String type= "@Component实例化bean ";
    public String getName (String name) {
        return name + "---" + type;
    }

}
