package com.winson.netty.v2.jdk;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;

/**
 * @author mike ian
 * @date 2025/5/30
 * @desc JDK NIO实现的Echo服务端Demo
 **/
public class JdkEchoServerDemoV2 {

    public static void main(String[] args) throws IOException {
        SelectorProvider selectorProvider = SelectorProvider.provider();
        Selector selector = selectorProvider.openSelector();

        ServerSocketChannel serverSocketChannel = selectorProvider.openServerSocketChannel();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 60001));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("server select start ");

        while (true) {
            int selectKey = selector.select();
            if (selectKey > 0) {
                var keyIterator = selector.selectedKeys().iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove();

                    if (key.isAcceptable()) {
                        handleAccept(key, selector);
                    } else if (key.isReadable()) {
                        handleRead(key);
                    }
                }
            } else {
                System.out.println("no events to process ... ");
            }
        }
    }

    private static void handleAccept(SelectionKey key, Selector selector) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = server.accept();
        if (socketChannel != null) {
            System.out.println("server accept client connection ... ");
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
            socketChannel.write(ByteBuffer.wrap("hello".getBytes()));
        }
    }

    private static void handleRead(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        int len = socketChannel.read(buffer);
        if (len > 0) {
            buffer.flip();
            byte[] data = new byte[buffer.remaining()];
            buffer.get(data);
            String content = new String(data);
            System.out.println("received from client: " + content);

            buffer.clear();
            buffer.put(("server response: " + content).getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        } else if (len == -1) {
            System.out.println("client closed connection");
            key.cancel();
            socketChannel.close();
        }
    }

}