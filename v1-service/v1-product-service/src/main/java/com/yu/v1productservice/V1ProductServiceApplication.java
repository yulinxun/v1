package com.yu.v1productservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@EnableDubbo
@MapperScan("com.yu.mapper")
@SpringBootApplication
public class V1ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(V1ProductServiceApplication.class, args);
	}

}
