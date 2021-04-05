package com.example.demo.netty.nio.fileChanel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel03 {

    public static void main(String[] args) throws IOException {
        File file = new File("d:\\file01.txt");

        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel01 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file02.txt");
        FileChannel fileChannel02 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true){

            // byteBuffer中的字节数是小于512 所以都能读到

            /**
             * 这里必须要有clear，因为read之后position=15
             * flip之后position=0，开始写入
             * 调用write之后，position=15了，尽管此时的read=0了，但是紧接着又执行了flip了还可以继续接着写入
             * 不断的死循环，不断的写文件了
             */
            byteBuffer.clear();//清空buffer

            int read = fileChannel01.read(byteBuffer);
            System.out.println("read=" + read);
            if (read == -1){
                break;
            }
            // 将buffer 中的数据写入到 fileChannel02 -- 2.txt
            byteBuffer.flip();
            fileChannel02.write(byteBuffer);
        }

        // 关闭相关流
        fileInputStream.close();
        fileOutputStream.close();
    }
}
