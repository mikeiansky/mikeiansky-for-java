package com.winson.sio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author winson
 * @date 2021/8/18
 **/
public class ServerDemoV1 {

    public static void main(String[] args) {

        ServerSocketChannel serverSocketChannel = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("172.16.2.39", 10001));
            List<SocketChannel> socketChannelList = new ArrayList<>();
            while (true){

                SocketChannel socketChannel = serverSocketChannel.accept();
                System.out.println("accept socket channel : " + socketChannel);
                socketChannelList.add(socketChannel);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("server demo v1 finish ... ");
    }

}
