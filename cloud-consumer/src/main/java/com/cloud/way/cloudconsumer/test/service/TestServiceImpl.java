package com.cloud.way.cloudconsumer.test.service;

import com.cloud.way.cloudconsumer.test.feign.TestRemoteFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author chongwei
 * @Description 测试
 * @date 2022/8/4
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TestRemoteFeign remoteFeign;

    @Override
    public String hello() {
        //当使用@LoadBalanced负载均衡注解时，需通过服务名调用而不是ip:port的形式
        String prefix = "http://" + "cloud-provider";
//        String prefix = "http://localhost:8081";
        String url = prefix + "/test/getHello";

        String restResult = restTemplate.postForObject(url, null, String.class);

        String feignResult = remoteFeign.getHello();

        return "restTemplate：" + restResult + "\n" + "remoteFeign：" + feignResult;
    }
}
