package com.winson.jdkapi.io.nio.v1.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2021/8/25
 **/
public class NioClientDemoV7 {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1);

        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("192.168.159.1", 10000));
            System.out.println("connect success ... ");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("nio client demo v7 finish ... ");
    }

}
