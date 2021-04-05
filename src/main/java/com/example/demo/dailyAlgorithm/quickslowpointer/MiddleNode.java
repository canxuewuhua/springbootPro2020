package com.example.demo.dailyAlgorithm.quickslowpointer;

/**
 * 返回链表的中间结点
 * 题目要求：
 * 给定一个带有头结点head的非空单链表 返回链表的中间节点
 * 如果有两个中间节点 则返回第二个中间节点
 * 1,2,3,4,5   返回3
 * 1,2,3,4,5,6  返回4
 */
public class MiddleNode {

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

        MyNode middleNode = middleNode(node1);
        System.out.println(middleNode.val);

    }

    public static MyNode middleNode(MyNode root){
        MyNode fast = root;
        MyNode slow = root;

        // 这个条件就是因为存在节点数目为奇数还是偶数的清空
        // 如果是偶数个 第一次 fast为3 slow为2  第二次fast为5 slow为3 第三次 fast为null slow为4
        // 如果为技术个 第一次 fast为3 slow为2  第二次fast为5 slow为3 因为fast.next为null，循环结束
        while (fast!=null && fast.next !=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
