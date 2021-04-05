package com.example.demo.springproxy.proxy;

import org.springframework.stereotype.Service;

@Service
public class Transaction {

    public void beginTransaction(){
        System.out.println("开启事务 ");
    }
    public void commit(){
        System.out.println("提交事务");
    }
}
