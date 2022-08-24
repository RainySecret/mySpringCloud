package com.cloud.way.cloudprovider.test.service;

import com.cloud.way.cloudprovider.messagequeue.service.TestProducer;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chongwei
 * @Description
 * @date 2022/8/24
 */
@Service
public class TestService {

    @Autowired
    private TestProducer testProducer;

    public String msgQueueTest() {
        Object sendResult = testProducer.kafkaSend("mine-test-01", "mine-test-"+RandomUtils.nextInt());
        return String.valueOf(sendResult);
    }
}
