package com.yu.v1mailservice.handler;

import com.yu.api.IMailService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yu
 * @date 2019/11/12 0012
 */
@Component
public class MailHandler {
    @Autowired
    private IMailService mailService;
    @RabbitHandler
    @RabbitListener(queues ="v1-activate-queue")
    public void processMessage(Map<String,String> params){
        String to=params.get("to");
        String username = params.get("username");
        mailService.sendActivateMail(to,username);
    }
}
