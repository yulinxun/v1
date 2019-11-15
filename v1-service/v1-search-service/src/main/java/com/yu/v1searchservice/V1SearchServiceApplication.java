package com.yu.v1searchservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableDubbo
@SpringBootApplication
@MapperScan("com.yu.mapper")
public class V1SearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(V1SearchServiceApplication.class, args);
	}

}
