package com.example.demo.dailyAlgorithm.red;

import java.util.List;

public abstract class RedPack {

    protected double money;
    protected int total;

    public RedPack(double money, int total){
        this.money = money;
        this.total = total;
    }

    public abstract List<Integer> sendRedPack();
}
