package com.yu.v1userservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableDubbo
@MapperScan("com.yu.mapper.user")
@SpringBootApplication
public class V1UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(V1UserServiceApplication.class, args);
	}

}
