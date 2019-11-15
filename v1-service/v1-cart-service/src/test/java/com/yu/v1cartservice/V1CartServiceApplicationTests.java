package com.yu.v1cartservice;

import com.yu.api.ICartService;
import com.yu.commons.pojo.ResultBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class V1CartServiceApplicationTests {
	@Autowired
	private ICartService cartService;
	@Resource(name = "myRedisTemplate")
	private RedisTemplate redisTemplate;
	@Test
	public void contextLoads() {
//		ResultBean add = cartService.add("123", 2L, 2);
//		System.out.println(add.getData());
		Map map= (Map) cartService.query("123").getData();
		for (Object o : map.keySet()) {
			System.out.println(map.get(o));
		}
	}
	@Test
	public void del() {
//		ResultBean add = cartService.add("123", 2L, 2);
//		System.out.println(add.getData());
		cartService.del("123",1L);
		Map map= (Map) cartService.query("123").getData();
		for (Object o : map.keySet()) {
			System.out.println(map.get(o));
		}
	}
	@Test
	public void update() {
//		ResultBean add = cartService.add("123", 2L, 2);
//		System.out.println(add.getData());
		cartService.update("123",2L,15);
		Map map= (Map) cartService.query("123").getData();
		for (Object o : map.keySet()) {
			System.out.println(map.get(o)+"-->"+o);
		}
	}


}
