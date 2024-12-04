package com.winson.jdkapi.io.bio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2021/8/20
 **/
public class ServerDemoV4 {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1);

        try {
            ServerSocket serverSocket1 = new ServerSocket();
//            serverSocket1.setReuseAddress(true);
            serverSocket1.bind(new InetSocketAddress("localhost", 10004));
            System.out.println("create server socket index 1 success ... ");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ServerSocket serverSocket2 = new ServerSocket();
//            serverSocket2.setReuseAddress(true);
            serverSocket2.bind(new InetSocketAddress("192.168.159.1", 10004));
            System.out.println("create server socket index 2 success ... ");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("server demo v4 finish ... ");

    }

}
