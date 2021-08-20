package com.winson.io.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author winson
 * @date 2021/8/17
 **/
public class NioServerDemoV2 {

    public static void main(String[] args) {

        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(10002));
            boolean active = true;
            while (active) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("accept socket channel : " + socketChannel);
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        boolean read = true;
                        while (read) {
                            int length = 0;
                            try {
                                length = socketChannel.read(byteBuffer);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            byteBuffer.flip();
                            System.out.println("socket read msg length : " + length);
                            if(length < 0){
                                read = false;
                                break;
                            }
                            byte[] buf = new byte[length];
                            for (int i = 0; i < length; i++) {
                                buf[i] = byteBuffer.get();
                            }
                            String result = new String(buf);
                            System.out.println("socket read msg result : " + result);
//                    if (result.equals("quit")) {
//                        read = false;
//                    }
                        }
                    }
                }).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
