package com.yu.springboottimer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootTimerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTimerApplication.class, args);
	}

}
