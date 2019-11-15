package com.yu.v1center.config;

import com.yu.commons.constant.MQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yu
 * @date 2019/11/6 0006
 */
@Configuration
public class TopicConfig {
    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange(MQConstant.V1_CENTER_EXCHANGE,true,false);
    }
    @Bean
    public Queue getQueue(){
        return new Queue(MQConstant.V1_CENTER_ITEM_QUEUE,true,false,false);
    }
    @Bean
    public Binding bindingExchange(Queue getQueue,TopicExchange topicExchange){
        return BindingBuilder.bind(getQueue).to(topicExchange).with("product.*");
        }
}
