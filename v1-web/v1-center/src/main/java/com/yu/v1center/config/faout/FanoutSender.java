package com.yu.v1center.config.faout;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yu
 * @date 2019/11/6 0006
 */
//@Component
public class FanoutSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(){
        rabbitTemplate.convertAndSend("v1_faout_exchange","","今天也要元气满满哦");
    }
}
