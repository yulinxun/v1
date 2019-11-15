package com.yu.v1sso;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class V1SsoApplicationTests {

	@Test
	public void contextLoads() {
		String string = UUID.randomUUID().toString();
		System.out.println(string);
	}

}
