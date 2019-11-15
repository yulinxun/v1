package com.yu.v1center.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yu
 * @date 2019/11/6 0006
 */
//@Component
public class TopicSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
        public void send(Long id){
            System.out.println("我更新了，你也更新下");
            rabbitTemplate.convertAndSend("v1-center-exchange","product.add",id);
    }
}
