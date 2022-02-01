package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author zhuchao
 * @date 2022/1/30 9:59 下午
 */
@EnableSwagger2
@EnableWebMvc
public class Swagger2 {


    @Bean
    public Docket createRestApi() {
        ApiInfo apiInfo =  new ApiInfoBuilder()
                        .description("description")
                        .contact(new Contact("name", "url", "email"))
                        .version("version")
                        .title("title")
                        .licenseUrl("licenseUrl")
                        .license("license")
                        .termsOfServiceUrl("termsOfServiceUrl")
                        .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.controller"))
                .paths(PathSelectors.any())
                .build();
    }


}
