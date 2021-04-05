package com.example.demo.kuangshenredis.base;

import redis.clients.jedis.Jedis;

public class TestPassword {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.199.106", 6379);

        //验证密码，如果没有设置密码这段代码省略
//        jedis.auth("password");

        jedis.connect(); //连接
        jedis.disconnect(); //断开连接

        jedis.flushAll(); //清空所有的key
    }
}
