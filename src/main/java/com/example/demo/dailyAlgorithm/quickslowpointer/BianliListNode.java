package com.example.demo.dailyAlgorithm.quickslowpointer;

public class BianliListNode {

    public static void main(String[] args) {
        MyNode node1 = new MyNode(1);
        MyNode node2 = new MyNode(2);
        MyNode node3 = new MyNode(3);
        MyNode node4 = new MyNode(4);
        MyNode node5 = new MyNode(5);
        MyNode node6 = new MyNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;

        MyNode p = node1;
        int m = 0;
        while(p!=null){
            p = p.next;
            m++;
        }
        System.out.println(m);
    }
}
