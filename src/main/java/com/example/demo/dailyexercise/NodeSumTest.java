package com.example.demo.dailyexercise;


/**
 *  求两个链表的和
 */
public class NodeSumTest {

    public static void main(String[] args) {

    }

    /**
     * 你有两个用链表代表的整数，其中每个节点包含一个数字。数字存储按照在原来整数中相反的顺序，使得第一个数字位于链表的开头。
     * 写出一个函数将两个整数相加，用链表形式返回和。
     * @param p
     * @param q
     * @return
     *
     * 比如 （7 ->1 ->6） + （5 ->9 ->2）  即617+295
     *      输出 （2 1 9），即 912
     */
    public static ListN mergeListNode(ListN p, ListN q){
        if (p == null){
            return q;
        }
        if (q == null){
            return p;
        }

        int sum = 0;
        int carry = 0;
        ListN head = new ListN(-1);
        ListN cur = head;
        while (p!= null || q!= null || carry!=0){
            int m = 0;
            int n = 0;

            if (p!=null){
                m = p.val;
                p = p.next;
            }
            if (q!=null){
                n = q.val;
                q = q.next;
            }

            sum = (m + n + carry)%10;
            ListN temp = new ListN(sum);
            cur.next = temp;
            cur = cur.next;

            carry = (m + n + carry)/10;
        }
        return head.next;
    }
}
