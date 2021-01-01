package com.example.demo.exercise.distribute_lock.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class RedisDistributeLock {
    /**
     * 基于缓存redis 实现分布式锁  使用setNx 当且仅当key不存在时，set一个key为val的字符串 若key存在则什么都不做，返回0
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/deduct_stock")
    public String deductStock(){
        String lockKey = "product_001";
        String clientId = UUID.randomUUID().toString();

        try{
            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, clientId);
            if (!result){
                return "error_code";
            }
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0){
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + "");
                System.out.println("扣减成功，剩余库存：" + realStock);
            }else{
                System.out.println("扣减失败，库存不足");
            }
        }finally {
            if (clientId.equals(stringRedisTemplate.opsForValue().get(lockKey))){
                stringRedisTemplate.delete(lockKey);
            }
        }

        return "end";
    }
}
