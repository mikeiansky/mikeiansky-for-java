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
 * @date 2021/8/26
 **/
public class NioMultiplexSingleThreadV1 {

    public static Selector selector;

    public static void startServer() {
        try {
            selector = Selector.open();

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress("localhost", 10001));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("server start ... ");

            while (true) {
                while (selector.select() > 0) {
                    Set<SelectionKey> keySet = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keySet.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isAcceptable()) {
                            acceptHandle(key);
                        } else if (key.isReadable()) {
                            readHandle(key);
                        } else {
                            writeHandle(key);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void acceptHandle(SelectionKey key) {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();

        try {
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println("accept socket : " + socketChannel);

            socketChannel.configureBlocking(false);

            socketChannel.register(selector, SelectionKey.OP_READ);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void readHandle(SelectionKey key) {

        SocketChannel socketChannel = (SocketChannel) key.channel();

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        try {
            int readLength = socketChannel.read(byteBuffer);

            if (readLength < 0) {
                System.out.println("close socket channel : " + socketChannel);
                socketChannel.close();
                return;
            }

            byteBuffer.flip();
            byte[] buf = new byte[byteBuffer.limit()];
            byteBuffer.get(buf);
            String readMessage = new String(buf);
            System.out.println("read message is : " + readMessage);
            byteBuffer.flip();
            socketChannel.register(selector, SelectionKey.OP_WRITE, byteBuffer);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void writeHandle(SelectionKey key) {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
        try {
            socketChannel.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        key.cancel();
    }

    public static void main(String[] args) {

        startServer();

        System.out.println("nio multiplex single thread version finish ... ");
    }

}
