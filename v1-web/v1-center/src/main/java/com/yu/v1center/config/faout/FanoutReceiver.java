package com.yu.v1center.config.faout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yu
 * @date 2019/11/6 0006
 */
//@Component
public class FanoutReceiver {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RabbitHandler
    @RabbitListener(queues = "one")
    public void processOne(String message){
        System.out.println(message);
    }
    @RabbitHandler
    @RabbitListener(queues = "two")
    public void processTwo(String message){
        System.out.println(message);
    }
}
