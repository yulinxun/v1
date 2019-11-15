package com.yu.v1center.config.simple;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yu
 * @date 2019/11/6 0006
 */
//@Component
public class Sender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(){
        rabbitTemplate.convertAndSend("v1_simple_queue","爱要大声说出来");
    }
}
