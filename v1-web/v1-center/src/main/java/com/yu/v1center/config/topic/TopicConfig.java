package com.yu.v1center.config.topic;

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
//@Configuration
public class TopicConfig {

    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange("v1_topic_exchange");
    }
    @Bean
    public Queue getQueue1(){
        return new Queue("topic_queue1");
    }
    @Bean
    public Queue getQueue2(){
        return new Queue("topic_queue2");
    }
    @Bean
    public Binding bindingExchange1(Queue getQueue1,TopicExchange topicExchange){
        return BindingBuilder.bind(getQueue1).to(topicExchange).with("path.*");
    }
    @Bean
    public Binding bindingExchange2(Queue getQueue2,TopicExchange topicExchange){
        return BindingBuilder.bind(getQueue2).to(topicExchange).with("path.#");
    }

}
