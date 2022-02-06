package com.example.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhuchao
 * @date 2022/2/6 10:01 上午
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpLogPrintAround {
    /**
     * 接口名称。参见ApiEnum
     */
    String interfaceName() default "";
}
