package com.example.demo.dailyexercise;

/**
 * 合并两个有序链表
 */
public class MergeSortListNTest {

    /**
     * 这是一道简单的链表相关的题目，但是对面试者基本功的要求还是很高，面试的时候一般是需要写出最优解的
     * 对于非递归解法，难点在于指针的设置和更新，这也是链表问题中常见的问题 需要我们重视
     * @param p
     * @param q
     * @return
     */
    public static ListN mergeListN(ListN p, ListN q){
        if (p == null){
            return q;
        }
        if (q == null){
            return p;
        }

        ListN pre = new ListN(-1), end = pre;
        while (p!= null && q!=null){
            if (p.val > q.val){
                end.next = q;
                end = q;
                q = q.next;
            }else{
                end.next = p;
                end = p;
                p = p.next;
            }
        }
        if (p == null){
            end.next = q;
        }else{
            end.next = p;
        }
        return pre.next;
    }
}
