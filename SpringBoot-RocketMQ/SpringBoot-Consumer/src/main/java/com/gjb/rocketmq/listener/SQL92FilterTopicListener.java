package com.gjb.rocketmq.listener;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @Description //TODO
 * @Date 2023/6/30 15:38
 * @Author 郭建波
 **/
@Component
@RocketMQMessageListener(
        consumerGroup = "SQL92FilterGroupBoot",
        topic = "SQL92FilterBoot",
        selectorType = SelectorType.SQL92,selectorExpression = "age>26"

)
public class SQL92FilterTopicListener implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt messageExt) {
        System.out.println("收到消息："+new String(messageExt.getBody(), Charset.defaultCharset())+"接收时间"+new Date());
    }
}
