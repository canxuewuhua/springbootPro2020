package com.example.demo.netty.nio.fileChanel;

import java.nio.ByteBuffer;

public class NIOByteBufferGetPut {

    public static void main(String[] args) {

        // 创建一个byteBuffer

        ByteBuffer byteBuffer = ByteBuffer.allocate(64);


        for (int i = 0; i < 64; i++) {
            byteBuffer.put((byte) i);
        }

        byteBuffer.flip();


        ByteBuffer readOnly = byteBuffer.asReadOnlyBuffer();
        System.out.println(readOnly.getClass());

        while(readOnly.hasRemaining()){
            System.out.println(readOnly.get());
        }

        // 会抛异常 ReadOnlyBufferException 因为只读buffer不允许写入
        readOnly.put((byte)101);


    }
}
