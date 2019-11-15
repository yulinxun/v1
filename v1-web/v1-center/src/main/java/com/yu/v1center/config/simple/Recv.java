package com.yu.v1center.config.simple;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yu
 * @date 2019/11/6 0006
 */
//@Component
@RabbitListener(queues = "v1_simple_queue")
public class Recv {

    @RabbitHandler
    public void process(String message){
        System.out.println("收到的消息是:"+message);
    }
}
