package com.yu.v1smsservice.handler;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.yu.api.ISMS;
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
public class SMSHandler {
    @Autowired
    private ISMS sms;
    @RabbitHandler
    @RabbitListener(queues ="v1-code-queue")
    public void processMessage(Map<String,String> params){
        String phone=params.get("phone");
        String code = params.get("code");
        sms.sendCode(phone,code);
    }
}
