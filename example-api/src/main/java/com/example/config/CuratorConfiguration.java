package com.example.config;




import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author zhuchao
 * @date 2022/2/6 9:09 下午
 */
@Configuration
@PropertySource(value = "classpath:/curator.properties")
public class CuratorConfiguration {
    @Value("${curator.retryCount}")
    private Integer retryCount;
    @Value("${curator.elapsedTimeMs}")
    private Integer elapsedTimeMs;
    @Value("${curator.connectString}")
    private String connectString;
    @Value("${curator.sessionTimeoutMs}")
    private Integer sessionTimeoutMs;
    @Value("${curator.connectionTimeoutMs}")
    private Integer connectionTimeoutMs;

    @Bean(initMethod = "start")
    public CuratorFramework curatorFramework() {
        return CuratorFrameworkFactory.newClient(
                connectString,
                sessionTimeoutMs,
                connectionTimeoutMs,
                new RetryNTimes(retryCount, elapsedTimeMs)
        );
    }
}
