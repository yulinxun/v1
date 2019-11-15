package com.yu.v1mailservice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yu.api.IMailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class V1MailServiceApplicationTests {
	@Reference
	private IMailService mailService;
	@Test
	public void contextLoads() {
		mailService.sendActivateMail("1061745620@qq.com","yu");
	}

}
