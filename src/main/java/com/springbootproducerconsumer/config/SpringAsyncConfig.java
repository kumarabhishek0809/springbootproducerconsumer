package com.springbootproducerconsumer.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
@Log4j2
public class SpringAsyncConfig {

    //todo wanted to do asynchronously but needs time to fix this.
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix("spring_thread_pool_executor_for_async");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
