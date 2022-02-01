package com.example.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class CorsConfig {

    public CorsConfig() {

    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        //设置host:port
        List<String> allowedOrigins = new ArrayList<>();
        allowedOrigins.add("http://localhost:8088");
        corsConfiguration.setAllowedOrigins(allowedOrigins);

        //设置headers
        List<String> allowedMethods = new ArrayList<>();
        allowedMethods.add("*");
        corsConfiguration.setAllowedMethods(allowedMethods);

        //设置methods
        List<String> allowedHeaders = new ArrayList<>();
        allowedHeaders.add("*");
        corsConfiguration.setAllowedHeaders(allowedHeaders);

        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        //3. 返回
        return new CorsFilter(corsConfigurationSource);
    }

}
