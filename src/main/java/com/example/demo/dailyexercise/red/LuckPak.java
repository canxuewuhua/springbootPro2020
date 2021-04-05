package com.example.demo.dailyexercise.red;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LuckPak extends RedPac{

    public LuckPak(double money, int total){
        super(money, total);
    }


    @Override
    public List<Integer> sendPak() {

        int balance = (int)super.money*100;
        int count = super.total;

        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < super.total -1; i++) {
            int amount = random.nextInt(balance/count*2);
            balance -=amount;
            count--;
            list.add(amount);
        }
        list.add(balance);
        return list;
    }
}
