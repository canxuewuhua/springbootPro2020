package com.example.demo.exercise.amount;

/**
 * 删除链表的倒数第N个节点
 * 解法1：不需要去遍历两次，一次即可，思路是，用两个指针p，q先指向头节点，让q指针走到第n个位置，然后两个指针同时往后走，
 * 走到q.next==null是说明p已经到达倒数第n个节点的前面的那个节点，此时删除下一个节点即可。
 *
 * 注意：当删除头节点和只有一个元素的情况。
 *
 */
public class Test {

    public static void main(String[] args) {


    }

    public static ListNode pinjieListNode(ListNode root,int n){
        if (root==null){
            return root;
        }

        ListNode p = root;
        ListNode q = root;
        int count = 0;
        while (q.next != null){
            count++;
            if (count<=n){
                q = q.next;
            }else{
                p = p.next;
                q = q.next;
            }
        }

        if (root.next == null || count+1 == n){
            root = root.next;
        }else{
            p.next = p.next.next;
        }
        return root;
    }
}
