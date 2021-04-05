package com.example.demo.dailyexercise.red;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RedTest {

    public static void main(String[] args) {


        double monney = 100;
        int total = 5;

        People p = null;
        List<People> pList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            p = new People("用户"+(i+1), 0);
            pList.add(p);
        }

        LuckPak luckPak = new LuckPak(monney,total);

        List<Integer> moneyList = luckPak.sendPak();

        // 倒叙防止移除乱序
        // 生成随机下标 进行匹配
        Random ran = new Random();
        for (int i = moneyList.size(); i >0 ; i--) {
            int m = ran.nextInt(moneyList.size());
            int n = ran.nextInt(pList.size());
            People peo = new People(pList.get(n).getName(), moneyList.get(m)/100.00);

            moneyList.remove(m);
            pList.remove(n);
            System.out.println(peo.getName()+"抢了"+peo.getAmount()+"元");
        }

        for (int i = 0; i < pList.size(); i++) {
            System.out.println(pList.get(i).getName()+"很遗憾没有抢到红包！");
        }
    }
}
