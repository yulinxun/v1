package com.yu.v1center.config.simple;

import com.rabbitmq.client.impl.AMQBasicProperties;
import org.springframework.amqp.core.AmqpAdmin;


import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @author yu
 * @date 2019/11/6 0006
 */
//@Configuration
public class SimpleConfig {


    @Bean
    public Queue getQueue(){

        return new Queue("v1_simple_queue");
    }
}
