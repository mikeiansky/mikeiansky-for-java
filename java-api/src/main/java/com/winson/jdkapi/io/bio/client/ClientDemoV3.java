package com.winson.jdkapi.io.bio.client;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author winson
 * @date 2021/8/20
 **/
public class ClientDemoV3 {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        int size = 100_000;
        AtomicLong count = new AtomicLong();
        ArrayList<Socket> sockets = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    Socket socket = new Socket("localhost", 10002);
                    count.incrementAndGet();
                    System.out.println("index : " + finalI + " , connect success : " + socket);

                    sockets.add(socket);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("queue size : " + count.get());

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

        System.out.println("client demo v3 finish ... ");


    }

}
