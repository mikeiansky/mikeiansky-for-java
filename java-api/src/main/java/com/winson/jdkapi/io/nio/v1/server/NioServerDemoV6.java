package com.winson.jdkapi.io.nio.v1.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2021/8/25
 **/
public class NioServerDemoV6 {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1);
        try {
            Selector selector = Selector.open();

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress("192.168.159.1", 10000));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true){

                int count = selector.select();
                System.out.println("select count : " + count);
                Set<SelectionKey> selectedKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectedKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if(key.isAcceptable()){
                        System.out.println("receive key : " + key);
                        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                        ssc.accept();
//                        key.cancel();
                    }
                }


//                SocketChannel socketChannel = serverSocketChannel.accept();
//                System.out.println("server accept socket channel : " + socketChannel);
//                if(socketChannel != null){
//                    socketChannel.configureBlocking(false);
//                    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
//                    socketChannel.read(byteBuffer);
//                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("nio server demo v6 finish ... ");
    }

}
