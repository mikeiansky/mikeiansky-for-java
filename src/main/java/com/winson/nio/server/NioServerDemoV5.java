package com.winson.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author winson
 * @date 2021/8/17
 **/
public class NioServerDemoV5 {

    public static void main(String[] args) {


        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(10005));

            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                System.out.println("accept socket channel : " + socketChannel);
                ByteBuffer buffer = ByteBuffer.allocate(1024);

                int readLength = 0;
                while (true) {
                    readLength = socketChannel.read(buffer);
                    if (readLength <= 0) {
//                        System.out.println("read msg length : " + readLength);
                        continue;
                    }
                    byte[] buf = new byte[readLength];
                    buffer.flip();
                    buffer.get(buf);
                    String msg = new String(buf);
                    System.out.println("read msg : " + msg);
                }
//                System.out.println("socket read complete : " + readLength);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("nio server demo v5 finish ... ");

    }

}

