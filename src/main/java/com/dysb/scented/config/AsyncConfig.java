package com.dysb.scented.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    // 스프링은 비동기적으로 메서드를 실행하기 위해서 SimpleAsyncTaskExecutor를 사용한다.
    // SimpleAsyncTaskExecutor는 요청이 오는대로 계속해서 쓰레드를 생성한다.

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(5);
        executor.setThreadNamePrefix("5bepoz");
        executor.initialize();
        return executor;
    }
    //  AsyncConfigurer 인터페이스를 구현하여 getAsyncExecutor() 를 오버라이딩 함으로써 default Executor가 내가 설정해둔 Executor가 된다.
    // 애플리케이션에서 @Async 를 사용했을 때 해당 Executor를 사용하게 된다.

    @Bean
    public Executor customExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setThreadNamePrefix("10bepoz");
        executor.initialize();
        return executor;
    }
}

