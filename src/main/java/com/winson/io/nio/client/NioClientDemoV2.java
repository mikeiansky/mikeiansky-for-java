package com.winson.io.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author winson
 * @date 2021/8/17
 **/
public class NioClientDemoV2 {

    public static void main(String[] args) {

        int size = 5;
        Thread[] threads = new Thread[size];

        for (int i = 0; i < size; i++) {
            int finalI = i;
            // 这种方式不行，在java当中，这些线程都是一个进程，也就是公用一个端口号
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("index : " + finalI + " connect start !");
                    Socket socket = new Socket();
                    try {
                        socket.connect(new InetSocketAddress("localhost", 10002));
                        String flag = "the index is : " + finalI;
                        socket.getOutputStream().write(flag.getBytes());
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        socket.close();
                        System.out.println("index : " + finalI + " connect success !");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < size; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
