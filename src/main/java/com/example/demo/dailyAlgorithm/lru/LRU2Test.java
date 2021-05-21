package com.example.demo.dailyAlgorithm.lru;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRU2Test extends LinkedHashMap<Integer, Integer> {

    /**
     * 面试官一般会让实现一个LRU算法
     * 要实现这个需求  需要用到一个哈希表和一个双向链表
     *
     * 在面试中面试官一般会期望读者能够自己实现一个简单的双向链表 而不是用语言自带的、封装好的数据结构
     * 在java语言中有类似的数据结构  linkedHashMap  但是这种做法不会符合面试官的要求
     */

    private int capacity;

    public LRU2Test(int capacity){
        super(capacity,0.75F,true);
        this.capacity = capacity;
    }

    public int get(int key){
        return super.getOrDefault(key, -1);
    }

    /**
     * 主要是这个方法
     * 插入数据
     * 如果hashmap中有，找到这个key，将其删除，然后将其放到链表的头部
     * 如果hashmap中没有，先判断链表满了没
     * @param key
     * @param value
     */
    public void put(int key, int value){
        super.put(key,value);
    }

    protected boolean removeElderEntry(Map.Entry<Integer,Integer> eldest){
        return size()> capacity;
    }

}
