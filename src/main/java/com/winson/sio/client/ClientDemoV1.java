package com.winson.sio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author winson
 * @date 2021/8/18
 **/
public class ClientDemoV1 {

    public static void main(String[] args) {
        int size = 40000;
        List<SocketChannel> socketChannelList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            try {
                SocketChannel socketChannel = SocketChannel.open();
//            socketChannel.bind(new InetSocketAddress("172.16.2.39", 20001));
                socketChannel.connect(new InetSocketAddress("172.16.2.39", 10001));

                socketChannelList.add(socketChannel);

                System.out.println("connect success index : " + i);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("connect error index : " + i);
            }
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(" client demo v1 finish ... ");
    }

}
