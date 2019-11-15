package com.yu.springbootmail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author yu
 * @date 2019/11/9 0009
 */
@Service
public class MailService {
    @Autowired
    private JavaMailSenderImpl sender;
    @Value("${mail.from}")
    private String from;
    @Autowired
    private TemplateEngine templateEngine;


    public void sendHtmlMail(String to,String subject,String context){
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(context, true);
            sender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public void sendTemplateMail(String to,String subject,String content){
        Context context=new Context();
        context.setVariable("username","yu");
        context.setVariable("url","https://www.aliyun.com/?utm_content=se_1000301881");
        content=templateEngine.process("email",context);
        sendHtmlMail(to,subject,content);
    }
}
