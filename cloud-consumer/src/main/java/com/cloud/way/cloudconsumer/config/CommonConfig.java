package com.cloud.way.cloudconsumer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Way
 * @description 公共配置类
 */
@Configuration
public class CommonConfig {
    @Autowired
    RestTemplateBuilder builder;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return builder.build();
    }

    @Bean
    @LoadBalanced
    /**
     * 需添加spring-boot-starter-webflux的依赖
     */
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}