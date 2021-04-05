package com.example.demo.netty.nio.fileChanel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel02 {

    public static void main(String[] args) throws IOException {


        // 将文件中的内容读出来

        File file = new File("d:\\file01.txt");

        FileInputStream fileInputStream = new FileInputStream(file);

        FileChannel fileChannel = fileInputStream.getChannel();

        // 创建缓存区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        // 将 通道的数据读入到buffer
        fileChannel.read(byteBuffer);

        System.out.println(new String(byteBuffer.array()));

        fileInputStream.close();


    }
}
