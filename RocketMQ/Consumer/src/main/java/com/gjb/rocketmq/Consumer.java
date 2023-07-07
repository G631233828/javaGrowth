package com.gjb.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @Description //TODO
 * @Date 2023/6/21 14:02
 * @Author 郭建波
 **/
public class Consumer {

    //RocketMq消费端
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("helloGroup2");
        consumer.setNamesrvAddr("192.168.0.56:9876");
        consumer.subscribe("helloTopic2","*");
        //设置消息的监听器
        consumer.setMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt msg : list ){
                    System.out.println("线程："+Thread.currentThread().getName()+"消费内容："+new String(msg.getBody(), Charset.defaultCharset()));
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;//消费成功
            }
        });


        consumer.start();
        System.out.println("Consumer Started.");
    }
}
