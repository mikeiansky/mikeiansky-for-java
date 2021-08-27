package com.winson.framework.netty.v1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2021/8/27
 **/
public class MainAppDemoV1 {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        SelectorThreadGroup stg = new SelectorThreadGroup(3);
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        stg.listen("192.168.159.1", 9999);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main app demo v1 finish ... ");
    }

}
