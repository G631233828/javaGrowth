package com.gjb.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.Charset;

/**
 * @Description //TODO
 * @Date 2023/6/21 10:03
 * @Author 郭建波
 **/
public class Producer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        //定义一个生产者  放一个组名
        DefaultMQProducer producer= new DefaultMQProducer("helloGroup3");
        //连接nameserver
        producer.setNamesrvAddr("192.168.0.56:9876");
        producer.setSendMsgTimeout(100000);
        //启动生产者
        producer.start();
        //设置消息发送的目的地
        String topic = "helloTopic3";
        // 循环发送消息
        for (int i = 0; i < 10; i++) {
            Message msg = new Message(topic,("RocketMQ消息："+i).getBytes(Charset.defaultCharset()));
           // Message message = new Message(topic,(msg+""+i).getBytes(Charset.defaultCharset()));
            //发送完成之后会返回响应结果
            SendResult result = producer.send(msg);
            System.out.println("生产者发送消息成功！发送状态："+result.getSendStatus());
        }
        producer.shutdown();


    }





}
