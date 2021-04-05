package com.example.demo.springproxy.proxycglib;

import org.springframework.stereotype.Service;

@Service
public class MyTransactionCglib {

    public void beginTransaction(){
        System.out.println("开启事务 ");
    }
    public void commit(){
        System.out.println("提交事务");
    }

}
