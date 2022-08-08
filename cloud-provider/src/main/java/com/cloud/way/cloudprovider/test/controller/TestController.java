package com.cloud.way.cloudprovider.test.controller;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chongwei
 * @Description 测试服务方
 * @date 2022/8/4
 */
@RestController
@RequestMapping("/test")
@Log
public class TestController {

    @PostMapping("getHello")
    public String getHello() {
        log.info("远程服务调用成功");
        return "服务调用成功";
    }
}
