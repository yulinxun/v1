package com.yu.springboottimer.task;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yu
 * @date 2019/11/12 0012
 */
@Component
public class SchedulerTask {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(cron = "0/5 * * * * ?")
    private void running() {
        Map<String, String> params2 = new HashMap<>();
        params2.put("to", "1061745620@qq.com");
        params2.put("username", "lin");
        rabbitTemplate.convertAndSend("v1-mail-exchange", "mail.activate", params2);
    }
}
