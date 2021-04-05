package com.example.demo.leetcode;

public class ReverseLinkListTest {

    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;

        // 链表反转
        ListNode newNode = reverseLinkList(node1);

    }

    public static ListNode reverseLinkList(ListNode head){
        // 初始化pre要等于null，next要等于null
        // 第一步 记录next节点
        // 第二步 当前节点的下一个指针反向指向pre
        // 第三步 pre向前移
        // 第四步 当前节点向后移
        ListNode pre = null,cur = head,next = null;
        while(cur !=null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
