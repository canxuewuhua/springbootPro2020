package com.example.demo.netty.nio.netty.simple;

import io.netty.util.NettyRuntime;

public class Test {
    public static void main(String[] args) {

        /**
         * 双cpu 4核  8线程 输出为4
         */
        System.out.println(NettyRuntime.availableProcessors());
    }
}
