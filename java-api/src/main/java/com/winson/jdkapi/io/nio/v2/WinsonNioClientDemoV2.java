package com.winson.jdkapi.io.nio.v2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;

/**
 * @author winson
 * @date 2022/6/4
 **/
public class WinsonNioClientDemoV2 {

    public static void main(String[] args) throws IOException, InterruptedException {
//        Socket socket = new Socket();
//        socket.connect(new InetSocketAddress(20001));
//        socket.close();
//        System.out.println("v2 client execute complete ... ");
//        Thread.sleep(5000);

        ServerSocketChannel channel = SelectorProvider.provider().openServerSocketChannel();
        System.out.println(channel.isOpen());

    }

}
