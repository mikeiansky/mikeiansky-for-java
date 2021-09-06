package com.winson.jdkapi.juc.socket_v2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author winson
 * @date 2021/5/17
 **/
public class TestNioHttp {

    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, serverSocketChannel.validOps());
        serverSocketChannel.bind(new InetSocketAddress(8003));

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 12);

        for (; ; ) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (key.isValid()) {

                    if (key.isAcceptable()) {
                        System.out.println("accept in thread : " + Thread.currentThread().getName());
                        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = ssc.accept();

                        if (socketChannel == null) {
                            continue;
                        }

                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);

                    } else {
                        System.out.println("other operation in thread : " + Thread.currentThread().getName());

                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        socketChannel.read(byteBuffer);
                        byteBuffer.clear();
                        String request = new String(byteBuffer.array());
                        System.out.println("request------->1");
//                        System.out.println(request);
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("request------->2");



                        byteBuffer.clear();
                        byteBuffer.put("HTTP/1.1 200 ok\n\nHello Shenzhen!\n".getBytes());
                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);
                        socketChannel.close();

                    }

                }
            }


        }


    }

}

