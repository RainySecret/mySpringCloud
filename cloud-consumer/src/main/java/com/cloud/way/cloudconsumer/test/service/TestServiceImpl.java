package com.cloud.way.cloudconsumer.test.service;

import com.cloud.way.cloudconsumer.test.feign.TestRemoteFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author chongwei
 * @Description 测试
 * @date 2022/8/4
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TestRemoteFeign remoteFeign;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public String hello() {
        //当使用@LoadBalanced负载均衡注解时，需通过服务名调用而不是ip:port的形式
        String prefix = "http://" + "cloud-provider";
//        String prefix = "http://localhost:8081";
        String url = prefix + "/provider/getHello";

        String restResult = restTemplate.postForObject(url, null, String.class);
        log.info("restTemplate：" + restResult);

        String feignResult = remoteFeign.getHello();
        log.info("remoteFeign：" + feignResult);

        Mono<String> result = webClientBuilder.build()
                .post()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
        String webClientResult = result.block();
        log.info("webClientResult：" + webClientResult);

        return feignResult;
    }
}
