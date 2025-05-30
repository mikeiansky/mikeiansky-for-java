package com.winson.netty.v1.nio;

import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

/**
 * @author winson
 * @date 2022/5/22
 **/
public class TestNioServerV1 {

    public static void main(String[] args) throws IOException {

        SelectorProvider provider = SelectorProvider.provider();
        Selector selector = provider.openSelector();

//        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        // 延迟注册感兴趣的事件
        SelectionKey selectionKey = serverSocketChannel.register(selector, 0);
//        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);

        serverSocketChannel.bind(new InetSocketAddress("localhost", 9001));

        int now = selector.selectNow();
        System.out.println("now : " + now);
        boolean running = true;
//        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (running) {
            int select = selector.select();
            System.out.println("select is : " + select);
            Set<SelectionKey> keySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                SelectionKey acceptKey = iterator.next();
                iterator.remove();
//                System.out.println(" acceptKey::isValid : " + acceptKey.isValid());

                if (acceptKey.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) acceptKey.channel();
                    SocketChannel socketChannel = server.accept();
                    System.out.println("accept socket channel : " + socketChannel);
                    socketChannel.configureBlocking(false);
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    socketChannel.register(selector, SelectionKey.OP_READ, buffer);
//                    socketChannel.register(selector, SelectionKey.OP_WRITE);
                } else if (acceptKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) acceptKey.channel();
//                    System.out.println("read data socket channel is : " + socketChannel);
//                    System.out.println("byteBuffer.capacity() : " + byteBuffer.capacity());
//                    System.out.println("byteBuffer.limit() : " + byteBuffer.limit());
//                    System.out.println("byteBuffer.position() : " + byteBuffer.position());
                    ByteBuffer byteBuffer = (ByteBuffer) acceptKey.attachment();
//                    byteBuffer.clear();
                    int length = socketChannel.read(byteBuffer);

//                    if (length == 0) {
//                        System.out.println("----------> ");
//                        continue;
//                    }
//                    if (length < 0) {
//                        acceptKey.cancel();
//                        System.out.println("accept key cancel" );
//                        continue;
//                    }
//                    acceptKey.interestOps(SelectionKey.OP_READ);
                    System.out.println("after read data length : " + length);
                    System.out.println("byteBuffer.capacity() : " + byteBuffer.capacity());
                    System.out.println("byteBuffer.limit() : " + byteBuffer.limit());
                    System.out.println("byteBuffer.position() : " + byteBuffer.position());
//                    byte[] data = new byte[byteBuffer.position()];
//                    byteBuffer.get(data);
                    System.out.println("data is : ***'" + new String(byteBuffer.array(), 0, byteBuffer.position()) + "'***");
//                    byteBuffer.clear();
//                    acceptKey.cancel();
//                    socketChannel.register(selector, SelectionKey.OP_READ);
//                    byteBuffer.flip();
//                    byte[] data = new byte[10];
//                    byteBuffer.get(data);
//                    System.out.println("read data is : " + new String(data));
//                    if(length<=0){
//                        socketChannel.close();
//                    }
                } else if (acceptKey.isWritable()){
                    System.out.println("acceptKey can write ---------> ");
                }

            }

        }

        System.out.println("app end ... ");
    }

}
