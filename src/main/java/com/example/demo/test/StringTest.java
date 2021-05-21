package com.example.demo.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 给你一个混合字符串 s ，请你返回 s 中 第二大 的数字，如果不存在第二大的数字，请你返回 -1 。
 *
 * 混合字符串 由小写英文字母和数字组成。
 * String s = "dfa12321afd";
 *         System.out.println(s.charAt(3));
 *         List<Integer> list = new ArrayList<>();
 *         for(int i=0;i<s.length();i++){
 *             if(s.charAt(i)>='0'&&s.charAt(i)<='9'){
 *                 list.add(new Integer(String.valueOf(s.charAt(i))));
 *             }
 *         }
 *
 *         Collections.sort(list);
 *         Collections.reverse(list);
 *         System.out.println(list);
 *         if (list.size()>1){
 *             for (int k = 1; k < list.size(); k++) {
 *                 int maxValue = list.get(0);
 *                 if (list.get(k)<maxValue){
 *                     System.out.println(list.get(k));
 *                 }
 *             }
 *         }
 */
public class StringTest {
    public static void main(String[] args) {

        String rate = "";
        String rate1 = rate.replace("%","");
        BigDecimal b1 = new BigDecimal(rate1).multiply(new BigDecimal("0.01"));
        System.out.println(b1);


    }
}
