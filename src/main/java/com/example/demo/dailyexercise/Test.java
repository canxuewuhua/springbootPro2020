package com.example.demo.dailyexercise;

import java.util.ArrayList;
import java.util.List;

/**
 * 设计一个函数，实现限流功能。在任一个时间间隔内（比如10秒钟），累计访问次数超过一定阈值（比如5次）。
 * 假设网络请求时间序列如下：
 * 10:09:59
 * 10:10:01
 * 10:10:03
 * 10:10:04
 * 10:10:06
 * 10:10:10
 * 10:10:11
 * 10:10:12
 * ....
 * 则最后一次请求要限流，因为从10:10:03到10:10:12，10秒内请求次数超过阈值。
 *
 * 每一次请求都会调用limit函数，参数是当次请求的时间戳。
 * boolean limit(long millis)
 *
 */
public class Test {

    public static void main(String[] args) {

    }
    public static long windowMillis;
    public boolean limit(long millis){
        List<Long> list = new ArrayList<>();
        if(list.size() <=5 && windowMillis<10){
            list.add(millis);
            windowMillis++;
            return true;
        }else {
            list.clear();
            windowMillis = 10;
            return false;
        }
    }
}
