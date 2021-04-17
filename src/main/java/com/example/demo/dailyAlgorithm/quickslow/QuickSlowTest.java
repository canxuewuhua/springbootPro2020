package com.example.demo.dailyAlgorithm.quickslow;

public class QuickSlowTest {

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
        node5.next = node3;
//        MyNode newNode = getMiddleNode(node1);
//        System.out.println(newNode.val);
//
//        int num = getBackward(node1, 2);
//        System.out.println(num);


//        System.out.println(isLoop(node1));

        System.out.println(entryNode(node1).val);
    }

    /**
     * 中间节点
     * 快慢指针，快指针每次走两步，慢指针每次走一步
     * 最后返回慢指针
     * 注意点：循环条件时，fast != NULL && fast->next != NULL，
     *  因为存在结点数目是奇数还是偶数的情况
     */
    public static MyNode getMiddleNode(MyNode head){
        MyNode p = head;
        MyNode q = head;
        while (p!=null && p.next!=null){
            p = p.next.next;
            q = q.next;
        }
        return q;
    }

    /**
     * 倒数第n个节点
     * 快慢指针 快指针先走n步
     * 然后快指针和慢指针一起走
     */
    public static int getBackward(MyNode head, int n){
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
     * 判断链表是否有环
     */
    public static MyNode isLoop(MyNode head){
        MyNode fast = head;
        MyNode slow = head;

        while (fast !=null && fast.next !=null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                return fast;
            }
        }
        return null;
    }

    /**
     * 寻找有环链表的入口
     */
    public static MyNode entryNode(MyNode head){
        MyNode meet = isLoop(head);
        if (meet == null){
            return null;
        }
        MyNode p = meet;
        MyNode q = head;
        while (p!=q){
            p = p.next;
            q= q.next;
        }
        return p;
    }

}
