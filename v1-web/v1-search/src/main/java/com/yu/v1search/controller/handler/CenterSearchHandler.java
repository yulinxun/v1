package com.yu.v1search.controller.handler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yu.api.ISearchService;
import com.yu.commons.constant.MQConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author yu
 * @date 2019/11/7 0007
 */
@Component
@RabbitListener(queues = MQConstant.V1_CENTER_SEARCH_QUEUE)
@Slf4j
public class CenterSearchHandler {
    @Reference
    private ISearchService searchService;
    @RabbitHandler
    public void process(Long id){
        log.info("处理了id为{}的消息",id);
        System.out.println("处理了id为"+id+"的消息");
        searchService.synById(id);
    }
}
