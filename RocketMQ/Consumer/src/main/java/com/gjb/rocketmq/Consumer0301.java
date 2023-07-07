package com.gjb.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @Description //TODO
 * @Date 2023/6/21 14:02
 * @Author 郭建波
 **/
public class Consumer0301 {

    //RocketMq消费端
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("orderlyProducerGroup");
        consumer.setNamesrvAddr("192.168.0.56:9876");
        consumer.subscribe("orderTopic","*");
        //设置从消费开始处
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
       //一个队列对应一个线程
        consumer.setMessageListener(new MessageListenerOrderly() {

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
              for(MessageExt m:list){
                  System.out.println("当前线程："+Thread.currentThread()+"队列id"+m.getQueueId()+"消息内容"+new String(m.getBody(),Charset.defaultCharset()));
              }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });


        consumer.start();
        System.out.println("Consumer2 Started.");
    }
}
