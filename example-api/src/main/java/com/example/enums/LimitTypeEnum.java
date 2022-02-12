package com.example.enums;

/**
 * @author zhuchao
 * @date 2022/2/12 6:05 下午
 */
public enum LimitTypeEnum {

    /**
     * 自定义key
     */
    CUSTOMER(1),
    /**
     * 请求者IP
     */
    IP(2);

    private int type;

    LimitTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

}