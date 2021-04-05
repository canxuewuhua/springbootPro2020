package com.example.demo.completablefutrue;

import com.example.demo.duoxiancheng.threadlocal.datesafe.DateUtilSafe;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
public class FutrueNumTest {
    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20,20,0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024), threadFactory);

        List<PartnerInfoDTO> partners = Arrays.asList(
                new PartnerInfoDTO("小米","BestPrice"),
                new PartnerInfoDTO("新浪","LetsSaveBig"),
                new PartnerInfoDTO("苏宁","MyFavoriteShop"),
                new PartnerInfoDTO("百度","BuyItAll")
        );

        List<Integer> partnerInfoDTOReturn = new ArrayList<>();

        List<Future<Integer>> futureArrayList = Lists.newArrayList();

        final List<Integer> taskList = Lists.newArrayList(2, 1, 3, 4, 5, 6, 7, 8, 9, 10);
        for (int i : taskList){
            Future<Integer> future = threadPoolExecutor.submit(new PartnerListShowCallable(i));
            futureArrayList.add(future);
        }

        /**
         * 获取任务结果的顺序:   按照提交顺序获取结果
         * 	CPU高速轮询，耗资源，可以使用，但不推荐
         */
        for (Future<Integer> fut : futureArrayList) {
            try {
                int k = fut.get(8L, TimeUnit.SECONDS);
                System.out.println("k的值为："+k);
                if (k != 0) {
                    partnerInfoDTOReturn.add(k);
                }
            } catch (Exception e) {
                System.out.println("获取k信息失败");
            }
        }
        System.out.println("上面的任务已经全部完成");






        threadPoolExecutor.shutdown();
    }
}
