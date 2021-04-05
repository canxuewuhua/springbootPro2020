package com.example.demo.netty.nio.fileChanel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel01 {

    public static void main(String[] args) throws IOException {

        String str = "hello 尚硅谷";

        /**
         * 将内容写入文件中
         */

        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file01.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();

        // 创建一个缓冲区 byteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 将str放入 byteBuffer
        byteBuffer.put(str.getBytes());

        // 对byteBuffer 进行flip
        byteBuffer.flip();

        // 将byteBuffer 数据写入fileChannel
        fileChannel.write(byteBuffer);

        fileOutputStream.close();

        // 这样就将hello 尚硅谷 写入了d盘下的file01.txt中
    }
}
