package com.yu.v1center.config.topic;

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

    public void send(){
        rabbitTemplate.convertAndSend("v1_topic_exchange","path.CBA","国内篮球赛事");
        rabbitTemplate.convertAndSend("v1_topic_exchange","path.NBA.CBA","全球篮球赛事");
    }
}
