package com.example.demo.dailyAlgorithm.red;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LuckRedPack extends RedPack {

    public LuckRedPack(double money, int total){
        super(money, total);
    }

    /**
     * 红包算法  2倍均值法
     * 红包金额为M 红包个数为N
     * 每次都从区间 [0,M/N*2]
     * @return
     */
    @Override
    public List<Integer> sendRedPack() {
        int balance = (int)super.money*100;
        int count = super.total;

        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < super.total -1; i++) {
            int amount = random.nextInt(balance/count*2);
            balance-=amount;
            count--;
            list.add(amount);
        }
        list.add(balance);
        return list;
    }
}
