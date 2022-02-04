package com.example.aop;


import com.example.annotation.ResultRecorder;
import javax.validation.constraints.Size.List;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author zhuchao
 * @date 2022/2/5 12:22 上午
 */
@Slf4j
@Aspect
@Component
public class ResultRecorderAspect {

    @Pointcut("@within(com.example.annotation.ResultRecorder)")
    public void pointcutController() {

    }

    @Around("pointcutController()")
    public Object pointcutController(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        ResultRecorder resultRecorder = joinPoint.getTarget().getClass().getAnnotation(ResultRecorder.class);
        if(resultRecorder!=null) {
            log.info(resultRecorder.value());
        }
        return result;
    }
}
