package com.winson.juc.socket_v1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author winson
 * @date 2021/5/14
 **/
public class TestNioClient {

    public static void main(String[] args) throws IOException {
        System.out.println(" test nio client start ... ");

        Selector selector = Selector.open();

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress("localhost", 9988));

        boolean run = true;

        while (run) {
            selector.select();
            Set<SelectionKey> keySet = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keySet.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();
                if (!key.isValid()) {
                    continue;
                }
                if(key.isConnectable()){
                    SocketChannel sc = (SocketChannel) key.channel();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_WRITE);
                    sc.finishConnect();
                }else if (key.isWritable()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);

                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    byte[] response = "Hello Winson!".getBytes();
                    buffer.limit(response.length);
                    buffer.put(response);
                    buffer.flip();
                    sc.write(buffer);
                } else if (key.isReadable()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int readLength = sc.read(buffer);

                    byte[] bfs = new byte[1024];
                    buffer.flip();
                    buffer.get(bfs, 0, readLength);

                    String response = new String(bfs, 0 , readLength);
                    System.out.println("response : " + response);
                    sc.close();
                }

            }

        }

        System.out.println(" test nio client end ... ");
    }

}
