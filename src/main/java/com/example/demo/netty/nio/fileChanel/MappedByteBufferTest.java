package com.example.demo.netty.nio.fileChanel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferTest {

    public static void main(String[] args) throws IOException {


        /***
         * 可以让文件直接在内存中修改   操作系统不需要拷贝一次  这个内存就是堆外内存
         *
         */

        RandomAccessFile randomAccessFile = new RandomAccessFile("d:\\file01.txt","rw");

        FileChannel fileChannel = randomAccessFile.getChannel();

        /**
         * 参数1 读写模式
         * 参数2 0 可以修改的起始位置
         * 参数3 映射到内存的大小 5这个是大小
         */
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(0,(byte)'H');
        mappedByteBuffer.put(3,(byte)'9');

        randomAccessFile.close();

        System.out.println("修改完毕");
    }
}
