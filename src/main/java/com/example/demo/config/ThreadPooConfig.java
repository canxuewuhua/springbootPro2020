package com.example.demo.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadPoolExecutor;

//@Component
@Slf4j
public class ThreadPooConfig {

    public static final String ACCOUNT_CRITICAL_ERROR = "account_critical_error";

    ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();

    {
        log.debug("初始化线程池开始");
        int queueCount = 10000;
        poolTaskExecutor.setQueueCapacity(queueCount);
        log.info("当前线程池配置的缓存对列数量queue:{}", queueCount);
        //获取CPU核数
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        log.info("当前服务器CUP核数availableProcessors：{}", availableProcessors);
        //线程池维护线程的最小数量
        int corePoolSize = availableProcessors + 1;
        poolTaskExecutor.setCorePoolSize(corePoolSize);
        log.info("当前线程池配置的核心线程数量corePoolSize:{}", corePoolSize);
        //线程池维护线程的最大数量
        int maxPoolSize = availableProcessors * 2;
        poolTaskExecutor.setMaxPoolSize(maxPoolSize);
        log.info("当前线程池配置的最大线程数量maxPoolSize:{}", maxPoolSize);
        //空闲线程的存活时间
        poolTaskExecutor.setKeepAliveSeconds(5000);
        poolTaskExecutor.initialize();
        //如果子线程缓存队列达到上限，就会使用当前线程去执行
        poolTaskExecutor.getThreadPoolExecutor().setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        log.debug("初始化线程池结束");
    }

    public void execute(Runnable task) {
        try {
            poolTaskExecutor.execute(task);
            log.debug("当前活动线程数：{}", poolTaskExecutor.getActiveCount());
            log.debug("核心线程数：{}", poolTaskExecutor.getCorePoolSize());
            log.debug("总线程数：{}", poolTaskExecutor.getPoolSize());
            log.debug("最大线程池数量：{}", poolTaskExecutor.getMaxPoolSize());
            log.debug("线程处理队列长度：{}", poolTaskExecutor.getThreadPoolExecutor().getQueue().size());
        } catch (Exception e) {
            log.error("{}线程池，执行线程出现异常task:{}", ACCOUNT_CRITICAL_ERROR, task, e);
            exceptionHandler(task);
        }
    }

    private void exceptionHandler(Runnable task) {
        try {
            Class<?> clazz = task.getClass();
            for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    Object fieldValue = field.get(task);
                    log.error("线程池，调用线程池出现异常，调用方信息（方便排查问题），fieldName:{},fieldValue:{},task:{}", fieldName, fieldValue, task);
                }
            }
        } catch (Exception e) {
            log.error("线程池，处理异常方法报错，task:{}", task, e);
        }
    }

}

