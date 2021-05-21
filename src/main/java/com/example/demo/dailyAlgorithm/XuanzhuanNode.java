package com.example.demo.dailyAlgorithm;

public class XuanzhuanNode {

    public static void main(String[] args) {
        /**
         * 旋转链表 leetcode
         * 旋转链表：我们可以先将给定的链表连接成环，然后将指定位置断开。int add = n - k%n; cur.next = head;
         */
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        int n = 1;
        ListNode iter = head;
        while (iter.next != null) {
            iter = iter.next;
            n++;
        }
        int add = n - k % n;
        if (add == n) {
            return head;
        }
        iter.next = head;
        while (add-- > 0) {
            iter = iter.next;
        }
        ListNode ret = iter.next;
        iter.next = null;
        return ret;
    }
}
