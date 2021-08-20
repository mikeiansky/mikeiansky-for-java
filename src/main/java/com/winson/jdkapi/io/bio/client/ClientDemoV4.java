package com.winson.jdkapi.io.bio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2021/8/20
 **/
public class ClientDemoV4 {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        Socket socket = new Socket();

        try {
            socket.connect(new InetSocketAddress("localhost", 10003));
            System.out.println("client demo v4 connect success ... ");

            byte[] buf = "1234567890123456".getBytes();
            socket.getOutputStream().write(buf);

            byte[] buf2 = "abcdefghijk".getBytes();
            socket.getOutputStream().write(buf2);

            socket.close();
            System.out.println("client demo v4 close ... ");

            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("client demo v4 complete ... ");

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("client demo v4 finish ... ");
    }

}
