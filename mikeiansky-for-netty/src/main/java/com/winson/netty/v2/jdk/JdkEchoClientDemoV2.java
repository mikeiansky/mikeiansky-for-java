package com.winson.netty.v2.jdk;

 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.net.InetSocketAddress;
 import java.nio.ByteBuffer;
 import java.nio.channels.ClosedChannelException;
 import java.nio.channels.SelectionKey;
 import java.nio.channels.Selector;
 import java.nio.channels.SocketChannel;
 import java.nio.channels.spi.SelectorProvider;

 /**
  * JDK NIO 实现的 Echo 客户端 Demo
  * 连接本地 127.0.0.1:60001，支持控制台输入与服务端交互
  *
  * @author mike ian
  * @date 2025/5/30
  */
 public class JdkEchoClientDemoV2 {

     public static void main(String[] args) throws IOException {
         // 获取 SelectorProvider 并打开 Selector
         SelectorProvider selectorProvider = SelectorProvider.provider();
         Selector selector = selectorProvider.openSelector();

         // 打开 SocketChannel 并设置为非阻塞
         SocketChannel socketChannel = selectorProvider.openSocketChannel();
         socketChannel.configureBlocking(false);

         // 分配缓冲区并注册连接事件
         ByteBuffer buffer = ByteBuffer.allocate(1024);
//         socketChannel.register(selector, SelectionKey.OP_CONNECT, buffer);
         SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ, buffer);
         new Thread(() -> {
             try {
                 Thread.sleep(2000);
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }
             selectionKey.interestOps(SelectionKey.OP_READ | SelectionKey.OP_CONNECT);
         }).start();

         // 发起连接
         socketChannel.connect(new InetSocketAddress("127.0.0.1", 60001));

         boolean running = true;
         while (running) {
             int readyChannels = selector.select();
             if (readyChannels > 0) {
                 for (SelectionKey key : selector.selectedKeys()) {
                     if (key.isConnectable()) {
                         // 处理连接事件
                         SocketChannel sc = (SocketChannel) key.channel();
                         if (sc.isConnectionPending()) {
                             sc.finishConnect();
                             System.out.println("连接成功");
                         }
                         // 连接成功后关注读写事件
                         key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                     } else if (key.isReadable()) {
                         // 处理读事件
                         ByteBuffer buf = (ByteBuffer) key.attachment();
                         buf.clear();
                         SocketChannel sc = (SocketChannel) key.channel();
                         int len = sc.read(buf);
                         if (len > 0) {
                             buf.flip();
                             byte[] data = new byte[buf.remaining()];
                             buf.get(data);
                             System.out.println("收到服务端数据: " + new String(data).trim());
                         } else if (len == -1) {
                             System.out.println("服务端关闭连接");
                             sc.close();
                             running = false;
                         }
                         // 继续关注读写事件
                         key.interestOps(key.interestOps() | SelectionKey.OP_WRITE | SelectionKey.OP_READ);
                     } else if (key.isWritable()) {
                         // 处理写事件，读取用户输入并发送
                         System.out.print("请输入要发送的数据：");
                         BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                         String line = reader.readLine();
                         if (line == null) {
                             running = false;
                             continue;
                         }
                         SocketChannel sc = (SocketChannel) key.channel();
                         sc.write(ByteBuffer.wrap(line.getBytes()));
                         // 写完后暂时不再关注写事件，避免空转
                         key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE | SelectionKey.OP_READ);
                     }
                 }
                 selector.selectedKeys().clear();
             }
         }

         System.out.println("客户端已退出...");
     }

 }