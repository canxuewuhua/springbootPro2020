package com.example.demo.duoxiancheng.cycliBarrier;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class CycTest {

    public static void main(String[] args)
    {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-%d").build();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 20, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());

        CyclicBarrier cyclicBarrier= new CyclicBarrier(3);

        CycWork work1= new CycWork(cyclicBarrier, "张三" );
        CycWork work2= new CycWork(cyclicBarrier, "李四" );
        CycWork work3= new CycWork(cyclicBarrier, "王五" );

        executor.execute(work1);
        executor.execute(work2);
        executor.execute(work3);

        executor.shutdown();

    }

}
