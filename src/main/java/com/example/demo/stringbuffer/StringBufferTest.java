package com.example.demo.stringbuffer;

/**
 * 扩容原理
 * 默认初始化容量(capacity)为16
 * 每次append的时候，如果达到了目前的capacity+1，就扩容，扩容后的capacity为原大小的2倍加1
 * 当append的字符比较长超过了2倍加1，则直接扩容到需要的容量大小
 *
 */
public class StringBufferTest {

    public static void main(String[] args) {
        //1:调用无参数构造器
        StringBuffer str = new StringBuffer();
        str.append("12345");
        System.out.println(str.capacity());//16
        System.out.println(str.length());//5
        str.append("67890123456");
        System.out.println(str.capacity());//16
        System.out.println(str.length());//16
        str.append("1223344556789056788963");
        str.append("345");

        // 新容量 = 原容量*2 + 2
        System.out.println(str.capacity());//34
        System.out.println(str.length());//17
        //2:调用有参数构造器
        str = new StringBuffer("123");
        // 初始化长度 = 默认长度 + 参数长度
        System.out.println(str.capacity());//19
        System.out.println(str.length());//3
    }
}
