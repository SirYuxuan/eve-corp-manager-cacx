/*
 * Copyright 2013-2021 Sir丶雨轩
 *
 * This file is part of Sir丶雨轩/basic-admin.

 * Sir丶雨轩/basic-admin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.

 * Sir丶雨轩/basic-admin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with Sir丶雨轩/basic-admin.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.yuxuan66.ecmc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 * @author Sir丶雨轩
 * @since 2021/10/28
 */
@Configuration
public class ThreadPoolConfig {

    /**
     * 获得Java虚拟机可用的处理器个数 + 1
     */
    private static final int THREADS = Runtime.getRuntime().availableProcessors() + 1;

    /**
     * 最大可创建的线程数
     */
    private final int maxPoolSize = 5 * THREADS ;;

    /**
     * 线程池名前缀
     */
    private static final String THREAD_NAME_PREFIX = "Async-Service-";

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor()
    {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(maxPoolSize * 100);
        executor.setCorePoolSize(maxPoolSize);
        // 队列最大长度
        int queueCapacity = 1024 * 5;
        executor.setQueueCapacity(queueCapacity);
        // 线程池维护线程所允许的空闲时间
        int keepAliveSeconds = 300;
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        // 线程池对拒绝任务(无线程可用)的处理策略
        // CallerRunsPolicy：由调用线程（提交任务的线程）处理该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        executor.initialize();
        return executor;
    }
}
