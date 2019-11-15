package com.yu.springbootmail;


import com.yu.springbootmail.service.MailService;
import org.junit.Test;
import org.junit.platform.engine.support.descriptor.FileSystemSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringbootMailApplicationTests {

    @Autowired
    private JavaMailSenderImpl sender;
    @Value("${mail.from}")
    private String from;
    @Value("${mail.to}")
    private String to;
    @Autowired
    private MailService mailService;
    @Test
    public void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("标题");
        message.setText("123");
        sender.send(message);
    }

    @Test
    public void sendHtml() {
       mailService.sendHtmlMail(to,"ovo","<h1>626</h1>");
    }

    @Test
    public void sendFile() {
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject("标题");
            helper.setText("<h1>666</h1>", true);

            String filePath="D:\\project\\v1\\v1-temp\\springboot-mail\\src\\main\\resources\\static\\124.jpg";
            FileSystemResource fileSystemResource = new FileSystemResource(filePath);
            String fileName=filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName,fileSystemResource);
            sender.send(message);
        } catch (MessagingException e1) {
            e1.printStackTrace();
        }
    }
    @Test
    public void test(){
        mailService.sendTemplateMail(to,"这个一个标题","123");
    }
}
