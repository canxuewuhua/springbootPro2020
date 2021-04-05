package com.example.demo.dailyexercise.red;

import java.util.List;

public abstract class RedPac {

    protected double money;

    protected  int total;

    public RedPac(double money, int total){
        this.money = money;
        this.total =total;
    }

    public abstract List<Integer> sendPak();
}
