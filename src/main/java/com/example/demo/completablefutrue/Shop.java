package com.example.demo.completablefutrue;

import com.example.demo.duoxiancheng.threadlocal.datesafe.DateUtilSafe;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 查询商品的价格为同步方法，并通过sleep方法模拟其他操作。
 */
@Data
public class Shop {

    String name;
    public Shop(String name){
        this.name = name;
    }



    private static Random random = new Random();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //Random ran = new Random();
        // 方法调用返回下一个从这个随机数生成器的序列中均匀分布的0.0和1.0之间的double值
        //System.out.println(ran.nextDouble()*10);
        // 0 <= nextInt(n) < n
        //System.out.println(ran.nextInt(10));


        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20,20,0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024), threadFactory);


//        for (int i=0; i< 20;i++){
//            threadPoolExecutor.execute(
//                    ()->System.out.println(getPrice("15"))
//            );
//        }

//        for (int i = 0; i < 20; i++) {
//            System.out.println(getPrice("15"));
//        }


        acquireResult();

        threadPoolExecutor.shutdown();

    }

    private static List<Shop> shopList = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll")
    );

    private static List<String> findPriceSync(String product) {
        return shopList.stream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)))  //格式转换
                .collect(Collectors.toList());
    }

    /**
     * 第一个执行结果为hello h2t，因为要先睡上1分钟结果不能立即获取
     * join方法获取结果方法里不会抛异常，但是执行结果会抛异常，抛出的异常为CompletionException
     * get方法获取结果方法里将抛出异常，执行结果抛出的异常为ExecutionException
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void acquireResult() throws ExecutionException, InterruptedException {
        //getNow方法测试
        CompletableFuture<String> cp1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(60 * 1000 * 60 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "hello world";
        });

        System.out.println(cp1.getNow("hello h2t"));

        //join方法测试
        CompletableFuture<Integer> cp2 = CompletableFuture.supplyAsync((()-> 1 / 0));
        System.out.println(cp2.get());
//
//        //get方法测试
//        CompletableFuture<Integer> cp3 = CompletableFuture.supplyAsync((()-> 1 / 0));
//        System.out.println(cp3.get());
    }

    /**
     * 根据产品名查找价格
     * @param product
     * @return
     */
    public static double getPrice(String product){
        return calculatePrice(product);
    }

    public static double calculatePrice(String product){
        delay();
        //random.nextDouble()随机返回折扣
        return random.nextDouble()*product.charAt(0) + product.charAt(1);
    }

    /**
     * 通过睡眠模拟其他耗时操作
     * */
    private static void delay() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
