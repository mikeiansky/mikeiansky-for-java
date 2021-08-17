package com.winson.nio.server;

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
 * @date 2021/8/17
 **/
public class NioServerDemoV4 {

    public static void main(String[] args) {

        try {

            Selector selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(10004));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (true) {

                int selectCount = selector.select();
                System.out.println("select count : " + selectCount);
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> keys = selectionKeySet.iterator();

                while (keys.hasNext()) {
                    SelectionKey selectionKey = keys.next();
                    if (!selectionKey.isValid()) {
                        continue;
                    }
                    keys.remove();

                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                        System.out.println("accept server");
                        SocketChannel socketChannel = server.accept();
                        System.out.println("accept socket : " + socketChannel);
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
//                        selectionKey.cancel();
                    } else if (selectionKey.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        buffer.clear();
                        int readLength = socketChannel.read(buffer);
                        System.out.println("read channel : " + socketChannel + " , readLength : " + readLength);
                        selectionKey.cancel();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(" nio server demo v4 finish ... ");

    }

}
