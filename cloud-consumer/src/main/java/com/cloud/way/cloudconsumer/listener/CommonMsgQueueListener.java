package com.cloud.way.cloudconsumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author chongwei
 * @Description 消息队列消费监听器
 * @date 2022/8/24
 */
@Component
@Slf4j
public class CommonMsgQueueListener {


    @KafkaListener(topics = "mine-test-01")
    public void thirdPartAuditListen(String msgData) {
        log.info("监听到消息队列中的topic信息：" + msgData);
    }

}
