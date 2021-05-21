package com.example.demo.dailyAlgorithm.lru;

import java.util.HashMap;
import java.util.Map;


/**
 *    第一行    散列表   对应map的key   散列表能快速定位key
 *    第二行    双向链表  有头结点 尾节点 key对应该链表中的一个node节点
 */
public class LRUTest {

    private Map<Integer, DlinkedNode> cache = new HashMap<>();


    /**
     * 临时变量size 做累加使用
     */
    private int size;
    /**
     * map容量
     */
    private int capaticy;

    private DlinkedNode head,tail;

    public LRUTest(int capaticy){
        this.size = 0;
        this.capaticy = capaticy;

        // 初始化头部和尾部节点
        head = new DlinkedNode();
        tail = new DlinkedNode();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key){
        DlinkedNode node = cache.get(key);
        if (node ==null){
            return -1;
        }
        // 如果key存在 先通过哈希表定位 再移动到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value){
        DlinkedNode node = cache.get(key);
        if (node == null) {
            // 如果 key不存在，创建一个新的节点
            DlinkedNode newNode= new DlinkedNode(key, value);
            // 将创建的新节点放到map中
            cache.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            ++size;
            if (size > capaticy){
                // 如果超出容量 删除双向链表的尾部节点
                DlinkedNode tail = removeTail();
                // 从map中删除对应的项
                cache.remove(tail.key);
                --size;
            }
        }else{
            // 如果key存在 先通过哈希表定位，再修改value  并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    // 先判断map中是否有这个key
    // 如果有key 找到链表的位置，并且把这个元素移动到链表的头部（先移除再放到头结点）
    // 如果没有这个key 判断长度是否超过了map的容量 ①超过了 则插入头结点 删除尾结点 ②没超过 则插入头部


    /**
     * 移除中间节点
     * @param node
     */
    public void removeNode(DlinkedNode node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    /**
     * 移除尾结点
     */
    public DlinkedNode removeTail(){
        DlinkedNode res = tail.pre;
        removeNode(res);
        return res;
    }

    public void addToHead(DlinkedNode node){
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    public void moveToHead(DlinkedNode node){
        removeNode(node);
        addToHead(node);
    }

}
