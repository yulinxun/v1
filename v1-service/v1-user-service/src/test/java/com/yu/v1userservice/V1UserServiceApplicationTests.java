package com.yu.v1userservice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.yu.api.IUserService;
import com.yu.commons.pojo.ResultBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class V1UserServiceApplicationTests {
	@Autowired
	private IUserService userService;
	@Test
	public void contextLoads() {
		ResultBean resultBean = userService.checkUserNameIsExists("yu");
		System.out.println(resultBean.getData());
	}

}
