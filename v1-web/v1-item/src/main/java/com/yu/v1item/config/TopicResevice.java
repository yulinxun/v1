package com.yu.v1item.config;

import com.yu.commons.util.HttpClientUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yu
 * @date 2019/11/6 0006
 */
@Component
public class TopicResevice {

    @RabbitHandler
    @RabbitListener(queues = "v1-queue")
    public void process(Long id){
        System.out.println("收到的消息是："+id);
        HttpClientUtils.doPost("http://localhost:9095/item/createHtml/"+id);
    }
}
