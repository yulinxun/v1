package com.yu.v1mailservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.yu.api.IMailService;
import com.yu.commons.pojo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author yu
 * @date 2019/11/12 0012
 */
@Service
public class MailServcieImpl implements IMailService {
    @Autowired
    private JavaMailSenderImpl sender;
    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public ResultBean sendActivateMail(String to, String username) {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper=null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom("839177458@qq.com");
            helper.setTo(to);
            helper.setSubject("账号激活");

            Context context=new Context();
            context.setVariable("username",username);
            context.setVariable("url","https://www.aliyun.com/?utm_content=se_1000301881");
            String content=templateEngine.process("email",context);
            helper.setText(content, true);
            sender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("邮件发送失败");
            //TODO 插入数据表
            return new ResultBean("500","邮件发送失败");
        }
        System.out.println("邮件发送成功");
        return new ResultBean("200","邮件发送成功");
    }
}
