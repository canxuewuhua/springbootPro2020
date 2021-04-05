package com.example.demo.duoxiancheng.countdown;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.lang.reflect.Field;
import java.util.concurrent.*;


/**
 *  有三个工人在为老板干活，这个老板有一个习惯，就是当三个工人把一天的活都干完了的时候，他就来检查所有工人所干的活。
 *  Worker类
 *
 *  https://www.cnblogs.com/qlqwjy/p/10251610.html
 *  有关闭锁 信号量 Barrier栅栏  同步工具类
 *  闭锁：1、CountDownLatch  2、FutureTask
 *  信号量：Semaphore
 *  Barrier栅栏：1、CyclicBarrier栅栏(循环屏障) 2、Exchanger
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
//        ExecutorService executor = Executors.newCachedThreadPool();

        /**
         * 使用 线程池的方式创建线程
         * 创建线程池 keepAliveTime 表示空闲线程的存活时间
         */
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-%d").build();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 20, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());

        CountDownLatch latch = new CountDownLatch(3);

        Worker w1 = new Worker(latch, "张三");
        Worker w2 = new Worker(latch, "李四");
        Worker w3 = new Worker(latch, "王二");

        Boss boss = new Boss(latch);

        try {
            executor.execute(w3);
            executor.execute(w2);
            executor.execute(w1);

            executor.execute(boss);
        }catch (Exception e){
            System.out.println("{}线程池，执行线程出现异常task:{}"+ e);
            exceptionHandler(w3);
        }



        executor.shutdown();
    }

    private static void exceptionHandler(Runnable task) {
        try {
            Class<?> clazz = task.getClass();
            for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    Object fieldValue = field.get(task);
                    System.out.println("线程池，调用线程池出现异常，调用方信息（方便排查问题），fieldName:{},fieldValue:{},task:{}"+fieldName+fieldValue+task);
                }
            }
        } catch (Exception e) {
            System.out.println("线程池，处理异常方法报错，task:{}"+ task + e);
        }
    }
}
