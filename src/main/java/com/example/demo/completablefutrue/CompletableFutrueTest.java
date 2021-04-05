package com.example.demo.completablefutrue;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutrueTest {

    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-%d").build();
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(20, 20, 0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingDeque<>(1024), threadFactory);

        Long start = System.currentTimeMillis();
        //结果集
        List<String> list = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        //定长10线程池
//        ExecutorService exs = Executors.newFixedThreadPool(10);
        List<CompletableFuture<String>> futureList = new ArrayList<>();
        final List<Integer> taskList = Lists.newArrayList(2, 1, 3, 4, 5, 6, 7, 8, 9, 10);
        try {
            ////方式一：循环创建CompletableFuture list,调用sequence()组装返回一个有返回值的CompletableFuture，返回结果get()获取
            //for(int i=0;i<taskList.size();i++){
            //    final int j=i;
            //    //异步执行
            //    CompletableFuture<String> future = CompletableFuture.supplyAsync(()->calc(taskList.get(j)), exs)
            //        //Integer转换字符串    thenAccept只接受不返回不影响结果
            //        .thenApply(e->Integer.toString(e))
            //        //如需获取任务完成先后顺序，此处代码即可
            //        .whenComplete((v, e) -> {
            //            System.out.println("任务"+v+"完成!result="+v+"，异常 e="+e+","+new Date());
            //            list2.add(v);
            //        })
            //        ;
            //    futureList.add(future);
            //}
            ////流式获取结果：此处是根据任务添加顺序获取的结果
            //list = sequence(futureList).get();

            //方式二：全流式处理转换成CompletableFuture[]+组装成一个无返回值CompletableFuture，join等待执行完毕。返回结果whenComplete获取
            /**
             * 获取任务结果的顺序:   支持任务完成的先后顺序
             * API极端丰富，配合流式编程，速度飞起，推荐使用！
             */
            CompletableFuture[] cfs = taskList.stream().map(object -> CompletableFuture.supplyAsync(() -> calc(object), threadPoolExecutor)
                    .thenApply(h -> Integer.toString(h))
                    //如需获取任务完成先后顺序，此处代码即可
                    .whenComplete((v, e) -> {
                        System.out.println("任务" + v + "完成!result=" + v + "，异常 e=" + e + "," + new Date());
                        list2.add(v);
                    })).toArray(CompletableFuture[]::new);
            //等待总任务完成，但是封装后无返回值，必须自己whenComplete()获取
            CompletableFuture.allOf(cfs).join();
            System.out.println("任务完成先后顺序，结果list2=" + list2 + "；任务提交顺序，结果list=" + list + ",耗时=" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }

    }


    public static Integer calc(Integer i) {
        try {
            if (i == 1) {
                i = i*20;
                Thread.sleep(3000);//任务1耗时3秒
            } else if (i == 5) {
                i = i*50;
                Thread.sleep(5000);//任务5耗时5秒
            } else {
                Thread.sleep(1000);//其它任务耗时1秒
            }
            System.out.println("task线程：" + Thread.currentThread().getName() + "任务i=" + i + ",执行！+" + new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }


    /**
     * 82      *
     * 83      * @Description 组合多个CompletableFuture为一个CompletableFuture,所有子任务全部完成，组合后的任务才会完成。带返回值，可直接get.
     * 84      * @param futures List
     * 85      * @return
     * 86      * @author diandian.zhang
     * 87      * @date 2017年6月19日下午3:01:09
     * 88      * @since JDK1.8
     * 89
     */
    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
        //1.构造一个空CompletableFuture，子任务数为入参任务list size
        CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        //2.流式（每个子任务join操作后转换为list）往空CompletableFuture中添加结果
        return allDoneFuture.thenApply(v -> futures.stream().map(CompletableFuture::join).collect(Collectors.<T>toList()));
    }

    /**
     * @param futures Stream
     * @return
     * @Description Stream流式类型futures转换成一个CompletableFuture, 所有子任务全部完成，组合后的任务才会完成。带返回值，可直接get.
     * @author diandian.zhang
     * @date 2017年6月19日下午6:23:40
     * @since JDK1.8
     */
    public static <T> CompletableFuture<List<T>> sequence(Stream<CompletableFuture<T>> futures) {
        List<CompletableFuture<T>> futureList = futures.filter(f -> f != null).collect(Collectors.toList());
        return sequence(futureList);
    }
}
