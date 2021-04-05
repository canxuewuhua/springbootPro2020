package com.example.demo.hashmap.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

    /**
     *
     * sizeCtl
     * sizeCtl为0，代表数组未初始化，且数组的初始化容量为16
     * sizeCtl为正数，
     * 如果数组未初始化，那么其记录的是数组的初始化，那么其记录的是数组的初始容量，
     * 如果数组已经初始化，那么其记录的是数组的扩容阈，即数组的初始容量*0.75
     *
     * sizeCtl为-1，表示数组正在进行初始化
     * sizeCtl小于0，并且不是-1，表示数组正在扩容，-(1+n),表示此时有n个线程正在公共完成数组的扩容操作
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        final ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

        String s1 = "通话";
        String s2 = "重地";
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());

        new Thread(){
            @Override
            public void run() {
                concurrentHashMap.put("通话","11");
                System.out.println("--------------------");
            }
        }.start();

        Thread.sleep(1000);

        new Thread(){
            @Override
            public void run() {
                concurrentHashMap.put("重地","22");
                System.out.println("--------------------");
            }
        }.start();
    }
}
