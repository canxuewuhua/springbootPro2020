package com.example.demo.spi;

public class YellowService implements DubboService {
    public void sayHello() {
        System.out.println("我是YellowService服务实现");

    }
}
