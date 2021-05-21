package com.example.demo.dailyAlgorithm;

public class XiangjiaoNode {

    public static void main(String[] args) {

        /**
         * 两个链表是否相交 如果不想交就返回null，如果相交就返回相交的节点
         */
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB){
        if (headA == null || headB ==null){
            return null;
        }

        while (headA !=headB){
            headA = headA == null ? headB : headA.next;
            headB = headB == null ? headA : headB.next;
        }
        return headA;
    }
}
