
BIO是单向的stream FileInputStream对象只能进行数据的操作 而NIO中的通道是双向的

常用的Channel FileChannel  ServerSocketChannel SocketChannel

FileChannel常用的方法有哪些  read write transferFrom transferTo （零拷贝）

```netty的第20节课是对buffer和channel的总结

只有在连接、通道真正有读写的时候才会进行读写；大大减少系统开销
不必为每一个连接都创建一个线程 不用去维护多个线程

```

selector 选择器
netty的io线程NioEventLoop聚合了selector（选择器，也叫多路复用器）
可以同时处理成百上千个客户端连接

一个I/O线程可以并发处理N个客户端连接和读写操作，从根本上解决了传统同步阻塞I/O一个连接一个线程模型。

selector类是一个抽象类，它实现了Closeable接口
open()方法用于得到一个选择器对象

监控所有注册的通道 当其中有IO操作可以进行时 将对应的selectionKey加入到内部集合中并返回

selector相关方法   select 阻塞  
                 select(1000) 阻塞1000毫秒后返回
                 wakeup 唤醒selector
                 selectNow 不阻塞 立马返回
NIO非阻塞 网络编程原理分析
selector selectionKey ServerSocketChannel 和 SocketChannel
ServerSocketChannel监听 
1、当客户端连接时，会通过ServerSocketChannel得到socketChannel
2、将SocketChannel注册到selector上，通过register(selector,ops)
3、一个selector上可以注册多个selector上，注册之后返回一个selectionKey，会和该selector进行关联（集合）
4、selector进行监听，select方法返回有事件发生的通道的个数
5、进一步得到各个selectionKey（有事件发生的）
6、再通过selectionKey反向获取SocketChannel channel()方法
7、通过得到的channel，完成业务处理
8、代码撑腰。。
尚硅谷24节课是开始NIO的代码  编写一个NIO入门案例 服务器端 客户端

第28节课 ServerSocketChannel
open方法   configureBlocking方法 register(注册一个选择器)方法

SocketChannel具体负责进行读写操作 网络IO通道
NIO把缓存区的数据写入通道，或者把通道数据写入缓存区

open方法得到一个socketchannel通道，
finishConnect 如果上面的方法连接失败，接下来就要通过该方法完成连接操作
write read方法
第29节课 NIO 网络编程应用实例 群聊系统
实现多人群聊  进一步理解NIO非阻塞网络编程
实现功能，服务器端监听客户端时间   客户端发送消息，其他客户端能看到，服务器能看到 客户端下线都能广播
上线 离线

1、先编写服务器端
 1.1服务器端启动并监听6667
 1.2服务器端接受客户端信息，并实现转发 处理上线和离线
2、编写客户端
注意点：客户端在使用完要调用 iterator.remove()

零拷贝是网络编程的关键，很多性能优化都离不开

常用的零拷贝有mmap内存映射 和 sendFile 我们看看NIO怎么使用零拷贝

零拷贝不是没有copy，而是没有cpu拷贝，但是还是有DMA的copy
两次上下文切换 两次DMA的copy，没有cpu的copy 零拷贝

关键点：mmap适合小数据量的读写 sendFile适合大文件传输
mmap需要四次上下文切换 3次数据拷贝；sendFile需要三次上下文切换最少2次拷贝 linux2.4之后的优化实现了零拷贝
sendFile 可以利用 DMA 方式，减少 CPU 拷贝，mmap 则不能（必须从内核拷贝到 Socket 缓冲区）。
在这个选择上：rocketMQ 在消费消息时，使用了 mmap。kafka 使用了 sendFile。

零拷贝实例第34节课fileChannel.transferTo方法底层使用的就是零拷贝

AIO是异步不阻塞IO 引入了异步通道的概念
NIO是同步非阻塞（多路复用）

IO同步阻塞 到理发店理发，就一直等理发师，直到轮到自己理发
NIO同步非阻塞 到理发店理发，发现前面有其他人在理发，给理发师说下，先干其他事，一会再过来看是否轮到自己了
AIO异步非阻塞 给理发师打电话，让理发师上门服务，自己干其他事情，理发师自己来家里给你理发。

DMA是直接从外设中将数据复制到内核态

总结区分
sendFlie 分linux2.1和2.4 2.1的时候只是减少了一次上下文切换，copy次数没有减少 三次上下文切换，
三次copy（两次DMA拷贝(外设到内核态，socket-buffer到协议栈的DMA拷贝)，一次内核态和socket-buffer的cpu拷贝）
2.4 避免了从内核态到socket-buffer的cpu拷贝 
两次拷贝(DMA外设到内核态  内核态到协议栈的DMA拷贝) 三次切换 ，
但还有从内核态到socket-buffer的cpu拷贝，内核缓存区只会拷贝一些 offset 和 length 信息到 SocketBuffer，基本无消耗

举了一个例子 演示一个文件使用fileChannel.transferTo实现零拷贝相比传统IO速度要更快

-----第36节课真正开始netty的讲解------
jdk的NIO bug，例如臭名昭著的Epoll bug，他会导致selector空轮询，最终cpu 100%，直到jdk1.7版本该问题仍旧存在，没有根本解决

Netty 线程模型介绍

Reactor模式
1、基于i/o复用模型，多个连接共用一个阻塞对象。
2、基于线程池复用线程资源：不必为每个连接创建线程，将连接完成后的业务处理任务分配给线程进行处理，一个线程可以处理多个连接的业务。

Reactor是反应器模式 分发者模式或者通知者模式
Reactor模式，通过一个或者多个输入同时传递给服务器的模式基于事件驱动
服务器端程序处理传入的对个请求，并将它们同步分配到响应的处理线程，因此Reactor模式也叫dispatch模式

Reactor模式使用io复用监听事件，收到事件后，分发给某个线程 

Reactor模式分类
1、单Reactor单线程  缺点是单线程，无法完全发挥多核cpu的性能 
2、单Reactor多线程
3、主从Reactor多线程

主从Reactor多线程
1）、Reactor主线程 MainReactor 对象通过select 监听连接事件, 收到事件后，通过Acceptor 处理连接事件
2）、当 Acceptor  处理连接事件后，MainReactor 将连接分配给SubReactor 
3）、subreactor 将连接加入到连接队列进行监听,并创建handler进行各种事件处理
4）、当有新事件发生时， subreactor 就会调用对应的handler处理
5）、handler 通过read 读取数据，分发给后面的worker 线程处理
6）、worker 线程池分配独立的worker 线程进行业务处理，并返回结果
7）、handler 收到响应的结果后，再通过send 将结果返回给client
8）、Reactor 主线程可以对应多个Reactor 子线程, 即MainRecator 可以关联多个SubReactor

分层+缓存

Netty讲解
BossGroup selector accept  --->SocketChannel  ---->NIOSocketChannel--->注册到WorkGroup selector 上
WorkGroup selector  --->handler
当work线程监听到selector中通道 发生自己感兴趣的事件后，就进行处理

NIOEventLoop 循环监听

ChannelFuture是一个异步结果返回

调用者并不能立刻获取到结果，而是通过Future-Listener机制

Netty 的异步模型是建立在 future 和 callback 的之上的。callback 就是回调。重点说 Future，它的核心思想是：
假设一个方法 fun，计算过程可能非常耗时，等待 fun返回显然不合适。那么可以在调用 fun 的时候，立马返回一个 Future，
后续可以通过 Future去监控方法 fun 的处理过程(即 ： Future-Listener 机制)

ChannelFuture是一个接口 我们可以添加监听器，当监听事件发生时，就会通知监听器
netty中 在handler 处理中   可以使用callback 或者future 实现异步

Bootstrap 意思是引导，一个 Netty 应用通常由一个 Bootstrap 开始，主要作用是配置整个 Netty 程序，串联各个组件，
Netty 中 Bootstrap 类是客户端程序的启动引导类，ServerBootstrap 是服务端启动引导类

Netty心跳检测机制案例
实例要求:  
编写一个 Netty心跳检测机制案例, 当服务器超过3秒没有读时，就提示读空闲

1、当服务器超过5秒没有写操作时，就提示写空闲
2、实现当服务器超过7秒没有读或者写操作时，就提示读写空闲

第72节课梳理netty核心模块内容

第78节课
Netty 本身自带的 ObjectDecoder 和 ObjectEncoder 可以用来实现 POJO 对象或各种业务对象的编码和解码，
底层使用的仍是 Java 序列化技术 , 而Java 序列化技术本身效率就不高，存在如下问题
无法跨语言
序列化后的体积太大，是二进制编码的 5 倍多。
序列化性能太低
=> 引出 新的解决方案 [Google 的 Protobuf]


解码器 编码器 用了很长的篇幅进行讲解

TCP的粘包和拆包基本介绍

tcp是面向连接的，面向流的；  发送端为了将多个发给接收端的包，更有效的发给对方，使用了优化算法Nagle算法，
将多次间隔较小且数据量小的数据，合并成一个大的数据块，然后进行封包。

虽然提高了效率，但是接收端就难于分辨出完整的数据包了，因为面向流的通信是无消息保护边界的

由于TCP无消息保护边界，需要在接收端处理消息边界问题，也就是我们所说的粘包、拆包问题
假设客户端分别发送了两个数据包D1和D2给服务器，由于服务器一次读取到的字节数是不确定的，故存在以下四种情况
       D2  D1
       D2D1
       D2_2  D2_1D1
       D2D1_1 D1_2
       
看一个具体的实例:

1、要求客户端发送 5 个 Message 对象, 客户端每次发送一个 Message 对象
2、服务器端每次接收一个Message, 分5次进行解码， 每读取到 一个Message , 会回复一个Message 对象 给客户端.
解决tcp粘包 拆包问题
解决该问题主要在代码
     protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, ByteBuf out) throws Exception {
        System.out.println("MyMessageEncoder encode 方法被调用");
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getContent());
     }
     
   获取到服务器端每次读取数据长度
   这个问题解决，就不会出现服务器多读或少读数据的问题，从而避免的TCP 粘包、拆包
   
 
第92节课 netty源码讲解
Netty启动过程源码 doBind方法

EventExecutor在创建的时候线程数child EventExecutor类型 -->EventLoop ---> NioEventLoop

pipeline是一个双向链表 head tail  每一个一个context加到tail的前面
NIOServerSocketChannel的doBind()方法 safeSetSuccess(promise)方法为最后，之后loop循环

Netty接收请求源码讲解
accept之后  怎么把产生的channel注册到workGroup上？
processSelectKey(key, channel)
doReadMessages NioServerSocketChannel中的方法 得到一个NioSocketChannel 放到容器中

在read方法中，循环调用ServerSocket的pipeline的fireChannelRead方法，开始管道中的handler的ChannelRead方法

----将客户端连接注册到work线程池---
childGroup.register(child).addListener(new ChannelFutureListener())

Netty中的ChannelPipeline ChannelHandler ChannelHandlerContext
非常重要的核心组件

ChannelSocket和ChannelPipeline是一对一的关联关系  而pipeline内部的多个context形成了链表，context只是对handler的封装

当一个请求进来的时候，会进入socket对应的pipeline，并经过pipeline所有的handler（都要执行），就是设计模式的过滤器模式

ChannelPipeline入站 出站  
socket---->channelPipeline 入站 inbound handler
channelPipeline---> socket 出站 outbound handler

fireChannelRead()  入站的handler 找下一个inboud handler入站

--- 入站事件接口 ----
ChannelInboundHandler  channelActive channelInactive channelRead channelReadComplete
程序员重写一些方法，当发生关注的事件，需要在方法中实现我们的业务逻辑，因为当事件发生时，netty会回调对应的方法

------出站事件接口----
ChannelOutboundHandler
bind connect disconnect close read write flush 
出站操作都是一些连接和写出数据类似的方法
ChannelDuplexHandler  既能处理出站也能处理入站

解码器 二进制数据转换为java对象
编码器 将java对象转化为二进制数据

ChannelHandlerContext部分源码

分析ChannelPipeline ChannelHandler ChannelHandlerContext

RPC的基本介绍 远程过程调用
用netty写一个 dubbo rpc(基于netty4.1.20) 用到代理 模仿dubbo 消费者和提供者约定接口和协议
消费者远程调用提供者的服务 提供者返回一个字符串 消费者打印提供者返回的数据













