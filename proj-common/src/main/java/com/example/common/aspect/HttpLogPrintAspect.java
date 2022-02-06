package com.example.common.aspect;

import com.example.common.annotation.HttpLogPrintAround;
import com.example.common.utils.JsonFormatUtil;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * @author zhuchao
 * @date 2022/2/6 10:05 上午
 */
@Aspect
@Order(1)
@Component
@Slf4j
public class HttpLogPrintAspect  {

    @Around(value = "execution(* *(..)) && @annotation(logAnnotation)")
    public Object printLog(ProceedingJoinPoint jp, HttpLogPrintAround logAnnotation) throws Throwable {
        Object bizRet = null;
        String logPrefix = jp.toShortString();

        Object[] params = jp.getArgs();
        String interfaceName = logAnnotation.interfaceName();

        String paramsStr = getParamsStr(params);

        long time = 0;
        try {
            printStartLog(logPrefix, paramsStr);
            long start = System.currentTimeMillis();
            bizRet = jp.proceed();
            time = System.currentTimeMillis() - start;
        } finally {
            try {
                printEndLog(logPrefix, paramsStr, bizRet, time, interfaceName);
            } catch (Exception e) {
                log.error("HttpLogPrintAspect printEndLog Exception, params:{}", paramsStr, e);
            }
        }
        return bizRet;
    }

    private void printStartLog(String logPrefix, String paramsStr) {
        //或者本地或者redis或者elasticsearch或者hbase
    }

    private void printEndLog(String logPrefix, String paramsStr, Object business, long time, String interfaceName) {
        //或者本地或者redis或者elasticsearch或者hbase
    }

    private String getParamsStr(Object[] params) {
        String paramsStr = null;
        if (params != null && params.length > 0) {
            StringBuilder str = new StringBuilder();
            for (Object param : params) {
                if (null == param) {
                    str.append("\"null\"");
                } else {
                    if (!(param instanceof ServletRequest || param instanceof ServletResponse || param instanceof BindingResult)) {
                        str.append(JsonFormatUtil.toJSON(param));
                    }
                }
            }
            paramsStr = str.toString();
        }
        return paramsStr;
    }
}