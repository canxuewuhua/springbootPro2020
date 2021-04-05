package com.example.demo.netty.nio.fileChanel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class NIOGileChannel04 {

    public static void main(String[] args) throws IOException {


        FileInputStream fileInputStream = new FileInputStream("d:\\file01.txt");

        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file02.txt");

        FileChannel sourceChannel = fileInputStream.getChannel();

        FileChannel targetChannel = fileOutputStream.getChannel();


        // 使用transferForm完成拷贝
        targetChannel.transferFrom(sourceChannel,0,sourceChannel.size());

        sourceChannel.close();
        targetChannel.close();
        fileInputStream.close();
        fileOutputStream.close();

    }
}
