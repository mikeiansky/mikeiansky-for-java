package com.winson.netty.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2022/5/22
 **/
public class TestNioClientV1 {

    public static void main(String[] args) throws IOException, InterruptedException {

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 9001));
//        socket.connect(new InetSocketAddress("localhost", 9090));

        System.out.println("socket connect success ... : " + socket);

        // write data
        OutputStream out = socket.getOutputStream();
        out.write("hello".getBytes(StandardCharsets.UTF_8));
        out.flush();
        Thread.sleep(1000);
        out.write("world".getBytes(StandardCharsets.UTF_8));
//        out.close();
        CountDownLatch latch = new CountDownLatch(1);
        latch.await();
//        Thread.sleep(10000);
        System.out.println("client end .... ");
        socket.close();

    }

}
