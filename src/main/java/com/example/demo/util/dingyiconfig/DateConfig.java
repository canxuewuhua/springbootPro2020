package com.example.demo.util.dingyiconfig;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(UtilProperties.class)
public class DateConfig {

    @Bean
    public MyDateUtil getUtil(){
        return new MyDateUtil();
    }
}
