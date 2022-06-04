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
//            serverSocketChannel.register(selector, SelectionKey.OP_CONNECT);

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (true) {

                int selectCount = selector.select();
//                System.out.println("select count : " + selectCount);
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> keys = selectionKeySet.iterator();

                while (keys.hasNext()) {
                    SelectionKey selectionKey = keys.next();
                    if (!selectionKey.isValid()) {
                        continue;
                    }

                    keys.remove();
//                    if (selectionKey.isConnectable()) {
//                        System.out.println(" channel isConnectable");
//                    }
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel socketChannel = server.accept();
                        System.out.println("accept socket : " + socketChannel);
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        socketChannel.register(selector, SelectionKey.OP_WRITE);
                    } else if (selectionKey.isReadable()) {

                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
//                        System.out.println("socketChannel is connected " + socketChannel.isConnected());
                        buffer.clear();
                        int readLength = socketChannel.read(buffer);
                        if (readLength <= 0) {
                            continue;
                        }
                        byte[] buf = new byte[readLength];
                        buffer.flip();
                        buffer.get(buf);
                        String readResult = new String(buf);
                        System.out.println("read channel : " + socketChannel + " , readLength : " + readLength + ", readResult : " + readResult);

//                        buffer.clear();
//                        buffer.put("hello i am server".getBytes());
//                        socketChannel.write(buffer);

//                        socketChannel.register(selector, SelectionKey.OP_WRITE);

                    } else if(selectionKey.isWritable()){
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                        buffer.clear();
                        buffer.put("hello i am server".getBytes());
                        buffer.flip();
                        socketChannel.write(buffer);

                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(" nio server demo v4 finish ... ");

    }

}
