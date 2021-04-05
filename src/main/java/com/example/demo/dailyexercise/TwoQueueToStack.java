package com.example.demo.dailyexercise;

import java.util.LinkedList;

/**
 * 如何用两个队列实现栈
 */
public class TwoQueueToStack {

    public static LinkedList<Integer> q1 = new LinkedList<>();
    public static LinkedList<Integer> q2 = new LinkedList<>();

    public static boolean isEmpty(){
        return q1.isEmpty()&&q2.isEmpty();
    }

    public synchronized void push(int n){
        q1.add(n);
    }

    public synchronized int pop(){
        if (q1.size() == 1){
            return q1.poll();
        }else{
            while (q1.size()!=1){
                q2.add(q1.poll());
            }
            int temp = q1.poll();
            while (!q2.isEmpty()){
                q1.add(q2.poll());
            }
            return temp;
        }
    }
}
