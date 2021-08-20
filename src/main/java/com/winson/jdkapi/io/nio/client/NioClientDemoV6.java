package com.winson.jdkapi.io.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author winson
 * @date 2021/8/17
 **/
public class NioClientDemoV6 {

    public static void main(String[] args) {

        try {
            SocketChannel socketChannel = SocketChannel.open();

            socketChannel.connect(new InetSocketAddress("localhost", 10005));
            System.out.println("connect success");

            System.out.println("write msg 1 !!");
            String msg = "hello nio i am client demo v6 msg 1";

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(msg.getBytes());

            buffer.flip();

            socketChannel.write(buffer);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("write msg 2 !! ");

            String msg2 = "hello nio i am client demo v6 msg 2";

            buffer.clear();
            buffer.put(msg2.getBytes());
            buffer.flip();



            socketChannel.write(buffer);


//            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(" nio client demo v6 finish ... ");

    }

}
