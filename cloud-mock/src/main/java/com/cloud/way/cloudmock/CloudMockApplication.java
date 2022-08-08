package com.cloud.way.cloudmock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CloudMockApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudMockApplication.class, args);
    }

}
