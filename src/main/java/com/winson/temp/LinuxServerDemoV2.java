package com.winson.temp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2021/8/23
 **/
public class LinuxServerDemoV2 {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1);

        try {
//            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
//            serverSocketChannel.configureBlocking(false);
//            serverSocketChannel.accept();

            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("192.168.195.1", 10000));

            List<Socket> socketList = new ArrayList<>();

            while (true) {
                Socket socket = serverSocket.accept();
                socketList.add(socket);
                System.out.println("accept socket size : " + socketList.size());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("server finish ... ");
    }

}
