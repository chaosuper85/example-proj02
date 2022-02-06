package com.example.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhuchao
 * @date 2022/2/6 10:01 上午
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheAround {

    String cacheKeyPrefix();

    // 缓存Key中的参数,为需要构造成缓存Key的方法入参名称。必须为基本类型或String
    String[] cacheKeyParams() default {};

    // 缓存超时时间(秒)
    int cacheExpireInsecond();
}
