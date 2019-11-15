package com.yu.v1center.config.faout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yu
 * @date 2019/11/6 0006
 */
//@Configuration
public class FaoutConfig {
    @Bean
    public Queue getQueueOne(){

        return new Queue("one");
    }
    @Bean
    public Queue getQueueTwo(){

        return new Queue("two");
    }
    @Bean
    public FanoutExchange getExchange(){
        return new FanoutExchange("v1_faout_exchange");
    }
    @Bean
    public Binding bindingExchangeOne(Queue getQueueOne,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(getQueueOne).to(fanoutExchange);
    }
    @Bean
    public Binding bindingExchangeTwo(Queue getQueueTwo,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(getQueueTwo).to(fanoutExchange);
    }
}
