package com.example.common.aspect;

import com.example.common.annotation.CacheAround;
import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author zhuchao
 * @date 2022/2/6 10:01 上午
 */
@Aspect
@Order(1)
@Component
@Slf4j
public class CacheAspect {

    @Resource
    private RedisTemplate redisTemplate;


    private static final Class[] BASIC_AND_STRING_ClASSES = new Class[]{
            String.class,
            Byte.class,
            Short.class,
            Integer.class,
            Long.class,
            Float.class,
            Double.class,
            Boolean.class,
            Character.class
    };

    @Around(value = "execution(* *(..)) && @annotation(cacheAnnotation)")
    public Object cacheAround(ProceedingJoinPoint jp, CacheAround cacheAnnotation) throws Throwable {

        Object methodResult = null;

        // 构造缓存Key,作为Category后的arg
        String key = getCacheKey(jp, cacheAnnotation);
        Object cacheResult = redisTemplate.opsForValue().get(key);
        if(cacheResult != null) {
            return cacheResult;
        }

        methodResult = jp.proceed();

        // 写入缓存
        if (methodResult != null) {
            redisTemplate.opsForValue().set(key, methodResult, cacheAnnotation.cacheExpireInsecond());
        }

        return methodResult;
    }

    private String getCacheKey(ProceedingJoinPoint jp, CacheAround cacheAnnotation) throws Exception {
        Map<String, Object> methodParamNameValuePair = Maps.newHashMap();
        Object[] paramValues = jp.getArgs();
        String[] parameterNames = ((MethodSignature) jp.getSignature()).getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            String paramName = parameterNames[i];
            methodParamNameValuePair.put(paramName, paramValues[i]);
        }

        String cacheKey = cacheAnnotation.cacheKeyPrefix();
        String[] cacheKeyParams = cacheAnnotation.cacheKeyParams();
        for (String cacheKeyParam : cacheKeyParams) {
            if (StringUtils.isEmpty(cacheKeyParam)) {
                continue;
            }

            if (!methodParamNameValuePair.containsKey(cacheKeyParam)) {
                throw new Exception("");
            }

            Object parameterValue = methodParamNameValuePair.get(cacheKeyParam);
            if (!ArrayUtils.contains(BASIC_AND_STRING_ClASSES, parameterValue.getClass())) {
                throw new Exception("");
            }

            cacheKey += "_" + parameterValue;
        }

        return cacheKey;
    }


}
