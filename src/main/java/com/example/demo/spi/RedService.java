package com.example.demo.spi;

public class RedService implements DubboService {
    public void sayHello() {

        System.out.println("我是RedService服务实现");

    }
}
