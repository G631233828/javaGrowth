package com.gjb.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.Charset;

/**
 * @Description 支持异步
 * @Date 2023/6/21 10:03
 * @Author 郭建波
 **/
public class Producer02 {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        //定义一个生产者  放一个组名
        DefaultMQProducer producer= new DefaultMQProducer("helloGroup2");
        //连接nameserver
        producer.setNamesrvAddr("192.168.0.56:9876");
        producer.setSendMsgTimeout(100000);
        //启动生产者
        producer.start();
        //设置消息发送的目的地
        String topic = "helloTopic2";
        //发送一条异步消息
            Message msg = new Message(topic,("RocketMQ异步消息消息").getBytes(Charset.defaultCharset()));
           // Message message = new Message(topic,(msg+""+i).getBytes(Charset.defaultCharset()));
            //异步发送，需要传递异步回调对象
             producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("消息存储状态"+sendResult.getSendStatus());
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.println("消息发送出现异常");
                }
            });
            System.out.println("消息发送完成");
        producer.shutdown();


    }





}
