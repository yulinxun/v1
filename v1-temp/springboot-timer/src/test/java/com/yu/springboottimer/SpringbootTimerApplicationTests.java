package com.yu.springboottimer;

import com.yu.springboottimer.task.SchedulerTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringbootTimerApplicationTests {
	@Autowired
	private SchedulerTask task;
	@Test
	public void contextLoads() {
	}

	public static void main(String[] args) {

	}
}
