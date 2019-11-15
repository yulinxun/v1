package com.yu.v1smsservice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yu.api.ISMS;
import com.yu.commons.util.CodeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class V1SmsServiceApplicationTests {
	@Reference
	private ISMS sms;
	@Test
	public void contextLoads() {
		String code= CodeUtils.generateCode(6);
		sms.sendCode("15279206586", code);
	}

}
