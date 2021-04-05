package com.example.demo.dailyAlgorithm.red;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RedPackTest {

    public static void main(String[] args) {

        double money = 10.00;
        int total = 5;

        People p = null;
        List<People> pList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            // 给群组成员设置名称，初始化金额为0
            p = new People("用户"+(i+1), 0);
            pList.add(p);
        }
        // 调用随机生成红包方法
        LuckRedPack luckRedPack = new LuckRedPack(money, total);
        List<Integer> moneyList = luckRedPack.sendRedPack();

        // 循环金额红包 倒序 匹配金额下标和群组成员下标
        Random ran = new Random();
        for (int i = moneyList.size(); i > 0; i--) {
            int m = ran.nextInt(moneyList.size());
            int n = ran.nextInt(pList.size());
            People peo = pList.get(n);
            peo.setAmount(moneyList.get(m)/100.00);

            moneyList.remove(m);
            pList.remove(n);
            System.out.println(peo.getName()+"抢了"+peo.getAmount()+"元");
        }

        for (int i = 0; i < pList.size(); i++) {
            System.out.println(pList.get(i).getName()+"遗憾，没有抢到！");
        }
    }
}
