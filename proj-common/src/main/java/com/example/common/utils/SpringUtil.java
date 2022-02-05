package com.example.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author zhuchao
 * @date 2022/2/6 12:29 上午
 */
@Slf4j
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtil.applicationContext = applicationContext;
    }

    /**
     * id 为 bean-name
     * @param id
     * @return
     */
    public static Object getBean(String id) {
        Object r = null;
        try {
            if (applicationContext != null) {
                r = applicationContext.getBean(id);
                return r;
            }
        } catch (Exception e) {
            log.error("SpringUtil getBean Exception, id:{}", id, e);
        }
        return r;
    }

}
