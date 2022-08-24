package com.cloud.way.cloudprovider.messagequeue.service;

import lombok.SneakyThrows;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

/**
 * @author chongwei
 * @Description 消息队列生产者
 * @date 2022/8/24
 */
@Service
public class TestProducer {

    @Resource
    private KafkaTemplate kafkaTemplate;

    /**
     * kafka消息队列生产者
     * @param topic
     * @param message
     */
    @SneakyThrows(Exception.class)
    public Object kafkaSend(String topic, String message) {
        final String[] result = new String[1];
        ListenableFuture future = kafkaTemplate.send(topic, message);
        //默认异步发送，调用get()变为同步，也可调用方法get(long timeout, TimeUnit unit)
        future.get();
        //添加回调方法
        future.addCallback(new ListenableFutureCallback() {
            @Override
            public void onFailure(Throwable ex) {
                result[0] = ex.getMessage();
            }

            @Override
            public void onSuccess(Object res) {
                result[0] = res.toString();
            }
        });
        return result[0];
    }
}
