package com.example.demo.netty.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupChatServer {

    //定义属性

    private Selector selector;

    private ServerSocketChannel listenChannel;

    private static final int PORT = 6667;

    //构造器
    //初始化工作
    public GroupChatServer(){
        try{
            selector = Selector.open();

            listenChannel = ServerSocketChannel.open();

            listenChannel.socket().bind(new InetSocketAddress(PORT));

            listenChannel.configureBlocking(false);

            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void listen(){
        System.out.println("监听线程: " + Thread.currentThread().getName());
        try{
            // 循环处理
            while (true){
                int second = selector.select();
                if (second > 0){
                    // 有事件要处理
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();

                        if (key.isAcceptable()){
                            SocketChannel sc = listenChannel.accept();
                            // 将sc注册到selector 注册 并 可读的状态
                            sc.configureBlocking(false);
                            sc.register(selector, SelectionKey.OP_READ);
                            System.out.println(sc.getRemoteAddress()+"上线了");
                        }

                        if (key.isReadable()){
                            //处理读 通道是可读状态
                            readData(key);
                        }


                        iterator.remove();//// 手动从集合中移除当前的selectionKey，防止重复操作
                    }

                }else{
                    System.out.println("等待中。。。");
                }

            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 异常处理
        }
    }

    /**
     * 读取客户端消息
     */
    private void readData(SelectionKey key){
        // 取到关联的channel
        SocketChannel channel = null;

        try{
            // 得到channel
            channel = (SocketChannel)key.channel();

            //创建buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            if (count >0){
                String msg = new String(buffer.array());
                // 输出该消息
                System.out.println("from 客户端："+msg);

                // 向其他的客户端转发消息，专门写一个方法来处理
                sendInfoToOtherClients(msg, channel);
            }
        }catch (IOException e){
            try {
                System.out.println(channel.getRemoteAddress() + "离线了。。。");
                // 取消注册
                key.cancel();
                // 关闭通道
                channel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void sendInfoToOtherClients(String msg, SocketChannel self) throws IOException {
        System.out.println("服务器转发消息中。。。");
        System.out.println("服务器转发数据给客户端线程: " + Thread.currentThread().getName());
        // 遍历 所有注册到selector 上的socketchannel
        for (SelectionKey key : selector.keys()){
            // 通过key 取出对用的socketChannel
            Channel targetChannel = key.channel();

            // 排除自己
            if (targetChannel instanceof SocketChannel && targetChannel != self){
                // 转型
                SocketChannel dest = (SocketChannel)targetChannel;
                //将msg存到到buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                dest.write(buffer);
            }
        }
    }

    public static void main(String[] args) {
        // 创建一个服务器对象
        GroupChatServer server = new GroupChatServer();
        server.listen();
    }
}
