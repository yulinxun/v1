package com.yu.v1item.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yu
 * @date 2019/11/6 0006
 */
@Configuration
public class CommonConfig {
    @Bean
    public ThreadPoolExecutor initThreadPoolExecutor(){
        int corePoolSize=Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor pool=new ThreadPoolExecutor(corePoolSize,corePoolSize*2
                ,1L, TimeUnit.SECONDS,  new LinkedBlockingQueue<>(100));
            return pool;
    }
}
