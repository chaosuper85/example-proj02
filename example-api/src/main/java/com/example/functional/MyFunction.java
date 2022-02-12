package com.example.functional;

/**
 * @author zhuchao
 * @date 2022/2/10 11:16 下午
 */
@FunctionalInterface
public interface MyFunction<A,B> {
    B handler(A a1, A a2);
}
