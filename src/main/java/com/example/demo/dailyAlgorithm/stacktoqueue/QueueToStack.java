package com.example.demo.dailyAlgorithm.stacktoqueue;


import java.util.ArrayDeque;
import java.util.Queue;

/**
 *
 *java 的queue队列有三个方法  pop peek element
 * 共同点：都是返回队列中的首个元素
 * 不同点：poll：将首个元素从队列中弹出，如果队列是空的，就返回null
 *       peek：查看首个元素，不会移除首个元素，如果队列是空的就返回null
 *       element：查看首个元素，不会移除首个元素，如果队列是空的就抛出异常NoSuchElementException
 *
 *   两个队列实现栈的核心思想是：如果队列一种没有元素，队列二有元素，将其队列二的元素一次放入队列一种，直到最后一个元素，把它弹出
 *   两个栈实现队列的核心思想是：push的话就往一个栈中push即可
 *                          pop是先将一个有元素的栈不停的弹出放到一个空栈中，再弹出第二个栈
 */
public class QueueToStack {

    Queue<Integer> queue1 = new ArrayDeque<Integer>();
    Queue<Integer> queue2 = new ArrayDeque<Integer>();

    /**
     * 队列Queue 有方法 add poll isEmpty()
     * @param args
     */
    public static void main(String[] args) {
        QueueToStack qs = new QueueToStack();
        qs.push(2);
        qs.push(4);
        qs.push(7);
        qs.push(5);
        System.out.println(qs.pop());
        System.out.println(qs.pop());

        qs.push(1);
        System.out.println(qs.pop());
    }

    /**
     * 向栈内压入数据   push的话都很简单只管add
     * @param element
     */
    public void push(Integer element){
        // 两个队列都为空时，优先考虑 queue1
        if (queue1.isEmpty() && queue2.isEmpty()){
            queue1.add(element);
            return;
        }

        // 如果queue1为空，queue2有数据 直接放入queue2
        if (queue1.isEmpty()){
            queue2.add(element);
            return;
        }
        // 如果queue2为空，queue1有数据 直接放入queue1
        if (queue2.isEmpty()){
            queue1.add(element);
            return;
        }
    }

    /**
     * 从栈中弹出一个数据
     * @return
     */
    public Integer pop(){
        if (queue1.isEmpty() && queue2.isEmpty()){
            try{
                throw new Exception("stacj is empty");
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        // 如果queue1中没有元素，queue2中有元素，将其queue2中的元素依次放入queue1中，直到最后一个元素，弹出即可
        if (queue1.isEmpty()){
            while (queue2.size() > 1){
                queue1.add(queue2.poll());
            }
            queue2.poll();
        }

        // 如果queue2中没有元素，queue1中有元素，将其queue1中的元素依次放入queue2中，直到最后一个元素，弹出即可
        if (queue2.isEmpty()){
            while (queue2.size() > 1){
                queue2.add(queue1.poll());
            }
            queue1.poll();
        }

        return (Integer)null;
    }
}
