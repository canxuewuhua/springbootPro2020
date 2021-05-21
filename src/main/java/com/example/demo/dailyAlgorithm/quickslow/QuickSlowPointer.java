package com.example.demo.dailyAlgorithm.quickslow;

/**
 * 
 */
public class QuickSlowPointer {

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
        node5.next = node6;
        node6.next = node3;


//      System.out.println(getMiddleNode(node1));
//      System.out.println(getBackN(node1, 2));
//      System.out.println(isLoop(node1));
        System.out.println(entryMyNode(node1).val);
    }

    /**
     * 1、求链表中间节点 一个指针走两步 一个指针走一步，走两步的结束了，走一步的就正好到中间
     */
    public static int getMiddleNode(MyNode head){
        MyNode p = head;
        MyNode q = head;
        while (p !=null && p.next !=null){
            p = p.next.next;
            q = q.next;
        }
        return q.val;
    }

    /**
     * 2、找到链表的倒数第n个节点
     */
    public static int getBackN(MyNode head,int n){
        MyNode p = head;
        MyNode q = head;

        for (int i = 0; i < n - 1; i++) {
            p = p.next;
        }
        while (p.next!=null){
            p = p.next;
            q = q.next;
        }
        return q.val;
    }

    /**
     * 3、判断一个链表是否有环
     * 一个走两步 一个走一步 肯定会相交
     */
    public static MyNode isLoop(MyNode head){
        MyNode p = head;
        MyNode q = head;

        while (p!=null && p.next!=null){
            p = p.next.next;
            q= q.next;
            if (p == q){
                return p;
            }
        }
        return null;
    }

    /**
     *  4、找到有环链表的入口
     */
    public static MyNode entryMyNode(MyNode root){
        MyNode meet = isLoop(root);
        if (meet == null){
            return null;
        }else{
            MyNode p = root;
            MyNode q = meet;
            while (p!=q){
                p = p.next;
                q = q.next;
            }
            return p;
        }
    }

    /**
     * 5、两个链表相交的点
     */
    public static MyNode getIntersector(MyNode headA, MyNode headB){
        MyNode p = headA;
        MyNode q = headB;

        while (p!=q){
            p = p == null ? headB : p.next;
            q = q == null ? headA : q.next;
        }
        return p;
    }
}
