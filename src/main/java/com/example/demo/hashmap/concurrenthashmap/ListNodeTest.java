package com.example.demo.hashmap.concurrenthashmap;

import java.util.ArrayList;
import java.util.List;

public class ListNodeTest {

    public static void main(String[] args) {

        ListNode nodeA1 = new ListNode(2);
        ListNode nodeA2 = new ListNode(4);
        ListNode nodeA3 = new ListNode(3);
        nodeA1.next = nodeA2;
        nodeA2.next = nodeA3;
        nodeA3.next = null;

        ListNode nodeB1 = new ListNode(5);
        ListNode nodeB2 = new ListNode(6);
        ListNode nodeB3 = new ListNode(4);

        nodeB1.next = nodeB2;
        nodeB2.next = nodeB3;
        nodeB3.next = null;

        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();

        while(nodeA1!=null){
            listA.add(nodeA1.val);
            nodeA1 = nodeA1.next;
        }

        while(nodeB1!=null){
            listB.add(nodeB1.val);
            nodeB1 = nodeB1.next;
        }

        /**
         * for(变量初始化;循环条件;迭代语句){
         * 循环语句;
         * }
         * 执行顺序是这样的：第一次进入for循环后先初始化变量，判断循环条件，如果为真则执行一次循环语句，然后执行迭代语句，第一次循环执行完毕。
         * 仅第一次进入for循环时初始化变量，之后的循环都不执行此语句，所以第二次循环的时候，直接先判断循环条件，如果为真则再执行一次循环语句，
         * 接着迭代语句。如此循环，知道判断循环条件为假时，结束循环。
         */
        StringBuilder sbA = new StringBuilder();
        for (int i = listA.size() -1; i >=0; i--) {
            sbA.append(listA.get(i));
        }

        StringBuilder sbB = new StringBuilder();
        for (int i = listB.size() -1; i >=0; i--) {
            sbB.append(listB.get(i));
        }

        int newVal = Integer.parseInt(sbA.toString()) + Integer.parseInt(sbB.toString());
        String newStr = String.valueOf(newVal);
        char[] ch = newStr.toCharArray();

        if (ch.length == 0){
            return;
        }

        ListNode nodeH = null;
        ListNode nodeE = null;
        for (int i = ch.length - 1; i >=0; i--) {
            ListNode temp = new ListNode(Integer.parseInt(String.valueOf(ch[i])));
            if (i == ch.length - 1){
                nodeH = temp;
                nodeE = temp;
            }else{
                nodeE.next = temp;
                nodeE = nodeE.next;
            }
        }

        System.out.println(nodeH);

    }
}
