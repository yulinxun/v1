package com.yu.v1center.config.topic;

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
public class TopicReceiver {
    @Autowired
    private RabbitTemplate template;
    @RabbitHandler
    @RabbitListener(queues = "topic_queue1")
    public void process(String message){
        System.out.println("接收者1："+message);
    }
}
