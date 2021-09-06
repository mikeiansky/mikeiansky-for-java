package com.winson.jdkapi.io.bio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2021/8/20
 **/
public class ClientDemoV1 {

    public static void main(String[] args) {
        int size = 10;

        CountDownLatch latch = new CountDownLatch(6);

        Thread[] threads = new Thread[size];
        for (int i = 0; i < size; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> {
                Socket socket = new Socket();
                try {
                    socket.connect(new InetSocketAddress("localhost",10001));
                    System.out.println("connect success ... " + finalI);
                    String msg = "message from : " + finalI;
                    socket.getOutputStream().write(msg.getBytes());
                    System.out.println("write message success ... " + finalI);

                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("thread " + finalI + " end ... ");
            });
            threads[i].start();

        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
