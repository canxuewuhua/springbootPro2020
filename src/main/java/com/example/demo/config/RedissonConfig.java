package com.example.demo.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;

public class RedissonConfig {

    @Bean
    public Redisson redisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("").setDatabase(0);
        return (Redisson)Redisson.create(config);
    }
}
