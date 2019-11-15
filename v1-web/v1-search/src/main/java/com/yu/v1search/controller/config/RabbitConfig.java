package com.yu.v1search.controller.config;

import com.yu.commons.constant.MQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yu
 * @date 2019/11/7 0007
 */
@Configuration
public class RabbitConfig {
    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange(MQConstant.V1_CENTER_EXCHANGE,true,false);
    }
    @Bean
    public Queue getQueue(){
        return new Queue(MQConstant.V1_CENTER_SEARCH_QUEUE,true,false,false);
    }
    @Bean
    public Queue getDelQueue(){
        return new Queue(MQConstant.V1_CENTER_SEARCH_DEL_QUEUE,true,false,false);
    }
    @Bean
    public Binding bindingExchange(Queue getQueue, TopicExchange topicExchange){
        return BindingBuilder.bind(getQueue).to(topicExchange).with("product.add");
    }
    @Bean
    public Binding bindingExchangeDel(Queue getDelQueue, TopicExchange topicExchange){
        return BindingBuilder.bind(getDelQueue).to(topicExchange).with("product.del");
    }

}
