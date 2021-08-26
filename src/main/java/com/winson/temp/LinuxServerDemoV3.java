package com.winson.temp;

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
public class LinuxServerDemoV3 {
    public static Selector selector;

    public static void init() {
        try {
            selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress("192.168.159.130", 30001));
            System.out.println("bind socket port success ... ");

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("register accept selection key success ... ");

            while (true) {

                int size = selector.selectedKeys().size();
                System.out.println("current selector key size : " + size);

                while (selector.select() > 0) {

                    Set<SelectionKey> keySet = selector.selectedKeys();
                    Iterator<SelectionKey> keyIterator = keySet.iterator();
                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();
                        keyIterator.remove();
                        if (key.isAcceptable()) {
                            acceptHandle(key);
                        } else if (key.isReadable()) {
                            readHandle(key);
                        }
                    }

                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void acceptHandle(SelectionKey key) {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        try {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            System.out.println("accept socket channel is : " + socketChannel);

            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("register socket channel read success ... ");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readHandle(SelectionKey key) {
        System.out.println("ready read data huhu .. ");
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        try {
            int readLength = socketChannel.read(byteBuffer);
            System.out.println("read data length : " + readLength);
            if (readLength < 0) {
                System.out.println("read cancel and close client : " + socketChannel);
                key.cancel();
                socketChannel.close();
                System.out.println("real cancel and close client : " + socketChannel);
                return;
            }
            byte[] buf = new byte[readLength];
            byteBuffer.flip();
            byteBuffer.get(buf);
            String msg = new String(buf);
            System.out.println("read message is : " + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        init();
    }

}
