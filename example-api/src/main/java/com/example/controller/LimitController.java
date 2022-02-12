package com.example.controller;

import com.example.annotation.Limit;
import com.example.enums.LimitTypeEnum;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuchao
 * @date 2022/2/12 6:19 下午
 */
@RestController
public class LimitController {

    private static final AtomicInteger ATOMIC_INTEGER_1 = new AtomicInteger();
    private static final AtomicInteger ATOMIC_INTEGER_2 = new AtomicInteger();
    private static final AtomicInteger ATOMIC_INTEGER_3 = new AtomicInteger();

    /**
     * @description
     */
    @Limit(period = 1, count = 3)
    @GetMapping("/limitTest1")
    public int testLimiter1() {
        return ATOMIC_INTEGER_1.incrementAndGet();
    }

    /**
     * @description
     */
    @Limit(key = "customer_limit_test", period = 10, count = 3, limitType = LimitTypeEnum.CUSTOMER)
    @GetMapping("/limitTest2")
    public int testLimiter2() {
        return ATOMIC_INTEGER_2.incrementAndGet();
    }

    /**
     * @description
     */
    @Limit(key = "ip_limit_test", period = 10, count = 3, limitType = LimitTypeEnum.IP)
    @GetMapping("/limitTest3")
    public int testLimiter3() {
        return ATOMIC_INTEGER_3.incrementAndGet();
    }
}
