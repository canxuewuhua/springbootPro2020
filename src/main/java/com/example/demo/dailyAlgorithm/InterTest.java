package com.example.demo.dailyAlgorithm;

import com.example.demo.dailyexercise.ListN;

import java.util.HashMap;
import java.util.Map;

public class InterTest {

    public static void main(String[] args) {

        Map<Integer,Integer> map = new HashMap<>();
        // 循环map
        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            if (entry.getValue() == 1){
                int num = entry.getKey();
            }
        }
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB){
        if (headA ==null || headB == null){
            return null;
        }

        while (headA!=headB){
            headA = headA ==null ? headB : headA.next;
            headB = headB ==null ? headA : headB.next;
        }
        return headA;
    }
}
