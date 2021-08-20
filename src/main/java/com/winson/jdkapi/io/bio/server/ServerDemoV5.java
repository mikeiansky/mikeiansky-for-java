package com.winson.jdkapi.io.bio.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2021/8/20
 **/
public class ServerDemoV5 {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1);

        try {
            int backlog = 2;
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", 10005), backlog);

            Socket socket = serverSocket.accept();
            socket.setTcpNoDelay(true);
            System.out.println("server accept socket : " + socket);

            InputStream in = socket.getInputStream();
//            String line = null;
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//            while ((line = reader.readLine()) != null){
//                System.out.println("read line : " + line);
//            }

            byte[] buf = new byte[1024];
            while (in.read(buf) > 0) {
                String request = new String(buf);
                System.out.println("read result : " + request);
                String response = "echo -> " + request;
                socket.getOutputStream().write(response.getBytes());
            }

            latch.await();

            System.out.println("server close ... ");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("server demo v5 finish ... ");

    }

}
