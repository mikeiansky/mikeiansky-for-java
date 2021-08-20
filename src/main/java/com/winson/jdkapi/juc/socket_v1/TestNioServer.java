package com.winson.jdkapi.juc.socket_v1;

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
 * @date 2021/5/14
 **/
public class TestNioServer {

    public static void main(String[] args) throws IOException {
        System.out.println("test nio server start ... ");

        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9988));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        boolean run = true;
        while (run) {
//            SocketChannel socketChannel = serverSocketChannel.accept();
            selector.select();
//            System.out.println("accept one ... ");
            Set<SelectionKey> keySet = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keySet.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();

                if (!key.isValid()) {
                    continue;
                }

                if (key.isAcceptable()) {
                    System.out.println(" accept key is : " + key);
                    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    int readLength = socketChannel.read(buffer);

                    byte[] buf = new byte[1024];
                    buffer.flip();
                    buffer.get(buf, 0, readLength);
                    String content = new String(buf);
                    System.out.println("read content is : " + content);

                    byte[] response = "HTTP/1.1 200 ok\n\nHello World\n".getBytes();
                    buffer.flip();
                    buffer.limit(response.length);
                    buffer.put(response);
                    buffer.flip();
                    socketChannel.write(buffer);
                    socketChannel.close();
                }

            }

        }


        System.out.println("test nio server end ... ");
    }

}
