package com.example.demo.kuangshenredis.ping;

import redis.clients.jedis.Jedis;

/**
 * 修改redis的配置文件   bind 127.0.0.1   修改为bind 0.0.0.0  这样就可以支持外机连接了
 */
public class Ping {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("47.94.155.21",6379);
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
    }
}
