package com.example.aop;


import com.example.annotation.MyLog;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author zhuchao
 * @date 2022/2/4 11:41 下午
 */
@Slf4j
@Aspect
@Component
public class SysLogAspect {
    @Pointcut("@annotation(com.example.annotation.MyLog)")
    public void pointcut() {
    }
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long begintime = System.currentTimeMillis();
        Object result = point.proceed();
        long endtime = System.currentTimeMillis();
        long costtime = endtime - begintime;

        MethodSignature methodSignature = (MethodSignature)point.getSignature();
        Method method = methodSignature.getMethod();
        MyLog myLog = method.getAnnotation(MyLog.class);
        if (myLog != null) {
            log.info(myLog.value());
        }
        return result;
    }
}
