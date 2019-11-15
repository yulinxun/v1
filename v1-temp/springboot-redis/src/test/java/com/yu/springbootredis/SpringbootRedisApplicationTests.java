package com.yu.springbootredis;


import com.yu.springbootredis.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.lang.Nullable;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Resource(name = "redisTemplate1")
    private RedisTemplate redisTemplate1;
    @Test
    public void test(){
//        List<Student> list=new ArrayList<>();
        Map<String,Student> map=new HashMap<>();
        map.put("aa",new Student(1,"yu"));
        map.put("bb",new Student(1,"yu"));
       redisTemplate.opsForValue().set("map",map);
       System.out.println(redisTemplate.opsForValue().get("map"));
    }
    @Test
    public void contextLoads() {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            redisTemplate.opsForValue().set("k" + i, "v" + i);
        }
        Long end = System.currentTimeMillis();
        System.out.println(end - start);//1610520?
    }

    @Test
    public void BatchcontextLoads() {
        Long start = System.currentTimeMillis();
        redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Nullable
            @Override
            public  Object execute(RedisOperations redisOperations) throws DataAccessException {
                for (int i = 0; i < 100000; i++) {
                    redisTemplate.opsForValue().set("k" + i, "v" + i);//27309-_-
                }
                return null;
            }
        });

        Long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
