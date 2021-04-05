package com.example.demo.completablefutrue;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.Callable;

@Slf4j
public class PartnerListShowCallable implements Callable<Integer> {

    private int m;

    public PartnerListShowCallable(int m){
        this.m = m;
    }

    @Override
    public Integer call() throws Exception {
        try {
            if (m == 1) {
                m = m*20;
                Thread.sleep(3000);//任务1耗时3秒
            } else if (m == 5) {
               m = m*50;
                Thread.sleep(5000);//任务5耗时5秒
            } else {
                Thread.sleep(1000);//其它任务耗时1秒
            }
            System.out.println("task线程：" + Thread.currentThread().getName() + "任务i=" + m + ",执行！+" + new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return m;
    }
}
