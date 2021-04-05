package com.example.demo.completablefutrue;

import com.example.demo.duoxiancheng.threadlocal.datesafe.DateUtilSafe;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class FutrueTest {

    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20,20,0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024), threadFactory);

        List<PartnerInfoDTO> partners = Arrays.asList(
                new PartnerInfoDTO("小米","BestPrice"),
                new PartnerInfoDTO("新浪","LetsSaveBig"),
                new PartnerInfoDTO("苏宁","MyFavoriteShop"),
                new PartnerInfoDTO("百度","BuyItAll")
        );

        List<PartnerInfoDTO> partnerInfoDTOReturn = new ArrayList<>();

        List<Future<PartnerInfoDTO>> futureArrayList = Lists.newArrayList();
//        for (PartnerInfoDTO partner : partners){
//            Future<PartnerInfoDTO> future = threadPoolExecutor.submit(new PartnerListShowCallable(partner));
//            futureArrayList.add(future);
//        }
//
//        for (Future<PartnerInfoDTO> fut : futureArrayList) {
//            try {
//                PartnerInfoDTO partnerInfoDTO = fut.get(8L, TimeUnit.SECONDS);
//                if (partnerInfoDTO != null) {
//                    partnerInfoDTOReturn.add(partnerInfoDTO);
//                }
//            } catch (Exception e) {
//                System.out.println("获取商户信息失败");
//            }
//        }


//        // supplyAsync需要有返回值，runAsync不需要有返回值
//        partners.stream().map(a -> CompletableFuture.supplyAsync(() -> {
//            // 操作代码.....
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return a;
//        })).collect(Collectors.toList()).stream().map(CompletableFuture::join).collect(Collectors.toList());




        partners.stream().map(a -> CompletableFuture.supplyAsync(() -> {
            // 操作代码.....
            a.setPartnerCode("XIOAMI");
            a.setPartnerName("小米金融");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return a;
        }, threadPoolExecutor)).collect(Collectors.toList()).stream().map(CompletableFuture::join).collect(Collectors.toList());


        threadPoolExecutor.shutdown();
    }
}
