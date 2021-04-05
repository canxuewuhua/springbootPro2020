package com.example.demo.netty.nio.fileChanel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    public static void main(String[] args) throws IOException {


        // 创建ServerSocketChannel --> ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 得到一个selector对象
        Selector selector = Selector.open();

        // 绑定一个端口 在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        // 设置为非阻塞
        /**
         * 与Selector一起使用时，Channel必须处于非阻塞模式下。这意味着FIleChannel与Selector不能一起使用。
         */
        serverSocketChannel.configureBlocking(false);

        // 把serverSocketChannel注册到selector上 selector关心事件为 OP_ACCEPT
        /**
         * 当一个客户端连接到来时，OP_ACCEPT事件就绪 服务端监听，注册OP_ACCEPT事件后，就已准备好接受客户端的连接了
         */
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


        System.out.println("注册后的SelectionKey 数量为："+selector.keys().size());

        // 循环等待客户端连接
        while (true){
            if (selector.select(1000) == 0){
                // 没有事件发生
                System.out.println("服务器等待了1秒，无连接");
                continue;
            }

            // 如果返回的不是0 >0 表示已经获取到关注的事件  返回关注事件的集合
            // 通过selectionKeys 反向获取通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            System.out.println("selectionKeys数量："+selectionKeys.size());

            // 遍历set集合 ，使用迭代器遍历
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()){
                // 获取到selectionKey
                SelectionKey key = iterator.next();
                //根据key对应的通道 发生的事件做相应的处理
                if (key.isAcceptable()){
                    // 如果是isAcceptable代表有客户端来连接

                    // 给该客户端生成一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();//这个方法本身是阻塞的，但由于已经判断过有连接了所以执行很快
                    System.out.println("客户端链接成功，生成了一个 socketChannel"+socketChannel.hashCode());

                    socketChannel.configureBlocking(false);

                    // 将socketChannel注册到selector上 关注事件为OP_READ 同时给socketChannel关联一个buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                    System.out.println("客户端连接后，注册后的SelectionKey 数量为："+selector.keys().size());// 2,3,4
                }

                if (key.isReadable()){  //发生OP_READ事件
                    // 这个事件是一个读的事件
                    // 通过key 反向获取对应的channel
                    SocketChannel socketChannel = (SocketChannel)key.channel();

                    // 获取到该channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer)key.attachment();

                    // 将当前通道数据读到buffer中
                    socketChannel.read(buffer);
                    System.out.println("from 客户端 "+new String(buffer.array()));

                }

                // 手动从集合中移除当前的selectionKey，防止重复操作
                iterator.remove();
            }

        }
    }
}
