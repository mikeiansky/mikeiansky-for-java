package com.winson.netty.v1.echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2022/6/8
 **/
public class EchoConnectDemoV1 {

    public static void main(String[] args) throws IOException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Socket socket1 = new Socket();
        socket1.connect(new InetSocketAddress("localhost", 8007));
        System.out.println("socket1 connect ... ");
        Socket socket2 = new Socket();
        socket2.connect(new InetSocketAddress("localhost", 8007));
        System.out.println("socket2 connect ... ");
        Socket socket3 = new Socket();
        socket3.connect(new InetSocketAddress("localhost", 8007));
        System.out.println("socket3 connect ... ");
        latch.await();

        socket1.close();
        socket2.close();
        socket3.close();

        System.out.println("main complete ... ");

    }

}
