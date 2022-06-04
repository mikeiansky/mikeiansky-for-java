package com.winson.jdkapi.io.nio.v1.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author winson
 * @date 2021/8/17
 * @desc 这里的测试思考是，java是单进程应用, serversocket 每次connect 都会产生一个端口和连接
 **/
public class NioClientDemoV4 {

    public static void main(String[] args) {
        Object obj = new Object();
        new Thread(() -> {
            synchronized (obj) {
                for (; ; ) {
                }
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t1 = new Thread(() -> {

            Socket socket = new Socket();
            try {
                socket.connect(new InetSocketAddress("localhost", 10002));
                socket.getOutputStream().write("thread1 flag ".getBytes());
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            synchronized (obj) {
//            }
            System.out.println("thread 1 finish ... ");
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            Socket socket = new Socket();
            try {
                socket.connect(new InetSocketAddress("localhost", 10002));
                socket.getOutputStream().write("thread2 flag ".getBytes());
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            synchronized (obj) {
            }

            System.out.println("thread 2 finish ... ");
        });
        t2.start();

        synchronized (obj) {
        }
        System.out.println("demo v4 run finish ... ");
    }

}
