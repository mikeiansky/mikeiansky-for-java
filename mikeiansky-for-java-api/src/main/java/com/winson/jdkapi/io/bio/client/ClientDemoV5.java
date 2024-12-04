package com.winson.jdkapi.io.bio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2021/8/20
 **/
public class ClientDemoV5 {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1);
        int size = 10;

        for (int i = 0; i < size; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress("localhost", 10005));

                    System.out.println("connect success : " + finalI);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }


        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("client demo v5 finish ... ");

    }

}
