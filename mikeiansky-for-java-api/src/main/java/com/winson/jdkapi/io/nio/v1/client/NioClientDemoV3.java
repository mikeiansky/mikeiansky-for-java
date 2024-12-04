package com.winson.jdkapi.io.nio.v1.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author winson
 * @date 2021/8/17
 **/
public class NioClientDemoV3 {

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            Socket socket = new Socket();
            try {
                socket.connect(new InetSocketAddress("localhost", 10002));
                System.out.println("thread1 client connect success!");
                socket.getOutputStream().write("i am client2  thread1".getBytes());

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            Socket socket = new Socket();
            try {
                socket.connect(new InetSocketAddress("localhost", 10002));
                System.out.println("thread2 client connect success!");

                Thread.sleep(5000);

                socket.getOutputStream().write("i am client2 thread2".getBytes());

                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        thread2.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
