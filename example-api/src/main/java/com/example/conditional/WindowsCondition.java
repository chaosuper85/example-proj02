package com.example.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author zhuchao
 * @date 2022/2/9 3:04 下午
 */
public class WindowsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext,
            AnnotatedTypeMetadata annotatedTypeMetadata) {
        Environment environment = conditionContext.getEnvironment();
        if(environment.getProperty("os.name").contains("windows")) {
            return true;
        }
        return false;
    }
}
