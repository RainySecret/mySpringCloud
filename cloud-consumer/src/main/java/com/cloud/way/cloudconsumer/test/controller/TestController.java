package com.cloud.way.cloudconsumer.test.controller;

import com.cloud.way.cloudconsumer.test.service.TestService;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author chongwei
 * @Description 测试
 * @date 2022/8/4
 */
@RestController
@RequestMapping("/test")
@Log
public class TestController {

    private static final RateLimiter rateLimiter = RateLimiter.create(10);

    @Autowired
    private TestService testService;

    @GetMapping("/hello")
    public String hello(){
        if (!rateLimiter.tryAcquire(0, TimeUnit.SECONDS)) {
            return "访问过于频繁，请稍后重试";
        }
        try {
            log.info("开始调用远程服务");
            return testService.hello();
        } catch (Exception e) {
            log.info("调用远程服务失败");
            e.printStackTrace();
        }
        return "ha ha";
    }
}
