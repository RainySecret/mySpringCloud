package com.cloud.way.cloudconsumer.test.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author chongwei
 * @Description feign接口测试demo
 * @date 2022/8/4
 */
@FeignClient("cloud-provider")
public interface TestRemoteFeign {

    @PostMapping(value = "/test/getHello")
    String getHello();
}
