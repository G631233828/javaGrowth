package com.gjb.rocketmq;

import com.gjb.rocketmq.pojo.OrderStep;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 支持异步
 * @Date 2023/6/21 10:03
 * @Author 郭建波
 **/
public class Producer03 {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        //定义一个生产者  放一个组名
        DefaultMQProducer producer= new DefaultMQProducer("orderlyProducerGroup");
        //连接nameserver
        producer.setNamesrvAddr("192.168.0.56:9876");
        producer.start();
        String topic = "orderTopic";
        for(int i=0;i<50;i++) {
            OrderStep step = new OrderStep();
            step.setOrderId(i+1);
            step.setDesc("订单"+i);
            Message msg = new Message(topic, step.toString().getBytes(Charset.defaultCharset()));
            producer.sendOneway(msg);
        }

        System.out.println("消息发送完成");
        producer.shutdown();


    }





}
