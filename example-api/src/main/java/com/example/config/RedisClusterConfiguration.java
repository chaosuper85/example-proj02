package com.example.config;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/*
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
*/

/**
 * @author zhuchao
 * @date 2022/2/6 9:09 下午
 */
//@SpringBootConfiguration
//@PropertySource(value = "classpath:/redis-cluster.properties")
public class RedisClusterConfiguration {
/*
    @Value("${redis.cluster.nodes}")
    private String nodes;

    @Value("${redis.cluster.connectionTimeout}")
    private Integer connectionTimeout;

    @Value("${redis.cluster.soTimeout}")
    private Integer soTimeout;

    @Value("${redis.cluster.maxAttempts}")
    private Integer maxAttempts;

    @Value("${redis.cluster.password}")
    private String password;


    @Bean  //这个注解注入工厂的名称是方法名
    public JedisCluster jedisCluster() {
        Set<HostAndPort> set = new HashSet<>();
        HostAndPort hp = null;
        String[] nodeStr = nodes.split(",");
        if(nodeStr!=null&&nodeStr.length>0){
            for(int i=0;i<nodeStr.length;i++){
                String[] hostPort = nodeStr[i].split(":");
                if(hostPort!=null&&hostPort.length>0){
                    hp = new HostAndPort(hostPort[0],Integer.valueOf(hostPort[1]));
                    set.add(hp);
                }
            }
        }
        JedisCluster jedisCluster = new JedisCluster(set,connectionTimeout, soTimeout, maxAttempts, password, null);
        return jedisCluster;
    }


 */
}
