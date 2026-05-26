package com.chance.litestock.config.threadpool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * thread pool config
 */
@Configuration
public class ThreadPoolConfig {

    @Value("${async.executor.thread.corePoolSize:5}")
    private int corePoolSize;

    @Value("${async.executor.thread.maximumPoolSize:20}")
    private int maximumPoolSize;

    @Bean("executor")
    public Executor initProcessorThreadPool() {
        return new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                60,
                TimeUnit.SECONDS,
                new SynchronousQueue<>(true)
        );
    }
}