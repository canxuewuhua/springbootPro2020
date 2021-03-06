//package com.example.demo.exercise.distribute_lock.redis;
//
//import org.redisson.Redisson;
//import org.redisson.api.RLock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.UUID;
//
//@RestController
//public class RedissonLock {
//
//    /**
//     * redisson 锁续命
//     */
//
//    @Autowired
//    private Redisson redisson;
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @RequestMapping("/deduct_stock")
//    public String deductStock(){
//        String lockKey = "product_001";
//        String clientId = UUID.randomUUID().toString();
//
//        RLock redissonLock = redisson.getLock(lockKey);
//
//
//        try{
//
//            redissonLock.lock();
//            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
//            if (stock > 0){
//                int realStock = stock - 1;
//                stringRedisTemplate.opsForValue().set("stock", realStock + "");
//                System.out.println("扣减成功，剩余库存：" + realStock);
//            }else{
//                System.out.println("扣减失败，库存不足");
//            }
//        }finally {
//            redissonLock.unlock();
//        }
//
//        return "end";
//    }
//}
