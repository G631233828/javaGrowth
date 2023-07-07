package com.gjb.rocketmq.listener;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * @Description //TODO
 * @Date 2023/6/30 15:38
 * @Author 郭建波
 **/
@Component
@RocketMQMessageListener(
        consumerGroup = "orderlyConsumerBoot",
        topic = "orderlyTopicBoot",
        consumeMode = ConsumeMode.ORDERLY
)
public class OrderlyTopicListener implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt messageExt) {
        System.out.println("当前线程"+Thread.currentThread()+"队列Id"+messageExt.getQueueId()+"，消息内容"+new String(messageExt.getBody()));

    }
}
