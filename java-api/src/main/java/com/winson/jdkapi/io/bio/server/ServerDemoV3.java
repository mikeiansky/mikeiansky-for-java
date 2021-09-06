package com.winson.jdkapi.io.bio.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2021/8/20
 **/
public class ServerDemoV3 {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1);

        try {
            ServerSocket serverSocket = new ServerSocket();
//            serverSocket.setSoTimeout(5000);
//            serverSocket.setReceiveBufferSize(10);
            System.out.println("receive buffer1 size : " + serverSocket.getReceiveBufferSize());
            serverSocket.bind(new InetSocketAddress("localhost", 10003));
            System.out.println("receive buffer2 size : " + serverSocket.getReceiveBufferSize());

            try {
                Socket socket = serverSocket.accept();
                System.out.println("accept socket : " + socket);
                Thread.sleep(3000);
                byte[] buf = new byte[1024];
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                socket.getInputStream().read(buf);
//                String result = new String(buf);

                String result = reader.readLine();

                System.out.println("read result : " + result);

            }catch (Exception e){
                e.printStackTrace();
            }

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("server demo v3 finish ... ");
    }

}
