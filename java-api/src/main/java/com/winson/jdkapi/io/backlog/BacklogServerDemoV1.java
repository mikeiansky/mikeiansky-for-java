package com.winson.jdkapi.io.backlog;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2022/6/8
 **/
public class BacklogServerDemoV1 {

    public static void main(String[] args) throws IOException, InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        ServerSocket serverSocket = new ServerSocket();
        // backlog 是 没有 accept 的连接最大数，超过这个数量 client 会被 connect refuse
        serverSocket.bind(new InetSocketAddress(10002), 3);
        System.out.println("backlog server start ... ");
        latch.await();
        System.out.println("backlog server complete ... ");

    }

}
