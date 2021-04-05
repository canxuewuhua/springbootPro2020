package com.example.demo.dailyexercise;

public class Revers {

    public static void main(String[] args) {
        ListN n1= new ListN(1);
        ListN n2= new ListN(2);
        ListN n3= new ListN(3);
        ListN n4= new ListN(4);
        ListN n5= new ListN(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = null;

        ListN n = revers(n1);
        System.out.println("");
    }

    public static ListN revers(ListN root){
        ListN pre=null,cur = root,next = null;

        while (cur!=null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
