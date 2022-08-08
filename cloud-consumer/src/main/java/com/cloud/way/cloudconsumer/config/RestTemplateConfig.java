package com.cloud.way.cloudconsumer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Autowired
    RestTemplateBuilder builder;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return builder.build();
    }
}