package com.example.demo.dailyAlgorithm.stacktoqueue;

import java.util.Stack;

public class StackToQueueTest {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    /**
     * 栈 Stack 有方法push pop isEmpty()等方法
     * @param args
     */
    public static void main(String[] args) {
        StackToQueueTest sq = new StackToQueueTest();
        sq.push(1);
        sq.push(3);
        sq.push(5);
        sq.push(4);
        sq.push(2);

        System.out.println(sq.pop());
        System.out.println(sq.pop());
        sq.push(7);
        System.out.println(sq.pop());
    }

    /*
     * 队列的数据压入过程
     */
    public void push(Integer element) {
        stack1.push(element);
    }

    /**
     * 队列的数据弹出过程
     */
    public Integer pop(){
        if (stack2.size() <=0){
            while (stack1.size() > 0){
                stack2.push(stack1.pop());
            }
        }
        if (stack2.isEmpty()){
            try{
                throw new Exception("queue is empty");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        Integer head = stack2.pop();
        return head;
    }
}
