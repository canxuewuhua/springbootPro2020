package com.example.demo.dailyAlgorithm.quickslowpointer;

public class DeleteDaoshuNNode {

    /**
     * 删除链表的倒数第N个节点
     * p指针先走n步，p q再一起走，p走完了，q到的位置就是倒数n个位置，再q.next = q.next.next;
     * @param args
     */
    public static void main(String[] args) {

    }

    public static MyNode deleteNode(MyNode head,int n){
        MyNode p = head;
        MyNode q = head;
        for (int i = 0; i < n - 1; i++) {
            p = p.next;
        }

        if (p==null){
            return q.next;
        }
        while (p.next!=null){
            p = p.next;
            q = q.next;
        }
        q.next = q.next.next;
        return q;
    }
}
