package com.cloud.way.cloudprovider.test.controller;

import com.cloud.way.cloudprovider.test.service.TestService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chongwei
 * @Description 测试服务方
 * @date 2022/8/4
 */
@RestController
@RequestMapping("/provider")
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/getHello")
    public String getHello() {
        log.info("远程服务调用成功");
        return "服务调用成功";
    }

    @PostMapping("/test/msgQueue")
    public String testMsgQueue() {
        return testService.msgQueueTest();
    }
}
