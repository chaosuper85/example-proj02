package com.example;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zhuchao
 * @date 2022/2/1 7:13 下午
 */
//@EnableApolloConfig
//@EnableSwagger2
@SpringBootApplication
@MapperScan(value = "com.example.mapper")
public class ExampleApplication {

    public static void main(String[] args) {
        try{
            SpringApplication.run(ExampleApplication.class, args);
        }catch(Throwable e) {
            e.printStackTrace();
        }

    }
}
