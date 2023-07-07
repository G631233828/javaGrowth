package com.gjb.rocketmq;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description //TODO
 * @Date 2023/6/28 16:07
 * @Author 郭建波
 **/
@Getter
@Setter
@ToString
public class OrderStep {

    private long orderId;
    private String desc;

}
