package com.winson.nio.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author winson
 * @date 2021/8/17
 **/
public class NioServerDemoV1 {

    public static void main(String[] args) {

        try {
            Selector selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
//            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress("localhost", 10001));
//            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                System.out.println("accept socket channel : " + socketChannel);
                Socket socket = socketChannel.socket();
                InputStream in = socket.getInputStream();
                boolean quit = false;
                while (!quit) {
                    byte[] buf = new byte[1024];
                    int readLength = in.read(buf);
                    String result = new String(buf);
                    System.out.println("read result : " + result);
                    if (result.equals("quit") || readLength <= 0) {
                        quit = true;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("server main finish ... ");
    }

}
