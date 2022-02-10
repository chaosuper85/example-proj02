package com.example.conditional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhuchao
 * @date 2022/2/9 3:01 下午
 */

@Configuration
public class ConditionalConfig {
    @Bean("windowsBean")
    @Conditional(value = {WindowsCondition.class})
    public SystemBean systemWindowsBean() {
        return new SystemBean("windows", "xp");
    }
    @Bean("macBean")
    @Conditional(value = {MacCondition.class})
    public SystemBean systemMacBean() {
        return new SystemBean("mac", "osx");
    }
}
