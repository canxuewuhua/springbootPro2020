package com.example.demo.netty.nio.fileChanel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * 这个案例是想说，你可以设计byteBuffer数组 方便一个数组不够存，它可以存储在另外一个数组
 */

public class ScattingAndGathering {
    public static void main(String[] args) throws Exception{

        /**
         * scatting  将数据写入到buffer时，可以采用buffer数组 依次写
         * gathering 从buffer读取数据时 可以采用buffer数组 依次读
         */

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

        serverSocketChannel.socket().bind(inetSocketAddress);

        // 创建buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        /**
         * 等客户端连接
         */
        SocketChannel socketChannel = serverSocketChannel.accept();
        int msgLength = 8;
        // 循环的读取
        while(true){
            int byteRead = 0;
            while(byteRead < msgLength){
                long l = socketChannel.read(byteBuffers);
                byteRead += l;
                System.out.println("byteRead = " + byteRead);

                // 使用流打印 看看当前的这个buffer的position 和 limit
                Arrays.asList(byteBuffers).stream().map(byteBuffer -> "position=" + byteBuffer.position()+
                        ",limit="+byteBuffer.limit()).forEach(System.out::println);

                // 将所有的buffer进行flip
                Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());
            }

            // 将所有数据读出显示到客户端
            long byteWrite = 0;
            while (byteWrite < msgLength){
                long l = socketChannel.write(byteBuffers);
                byteWrite += l;
            }

            // 将所有的buffer进行复位
            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());

            System.out.println("byteRead:="+byteRead+",byteWrite="+byteWrite+"msgLength:"+msgLength);
        }
    }
}
