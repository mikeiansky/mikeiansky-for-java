package com.winson.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author winson
 * @date 2021/8/17
 **/
public class NioClientDemoV5 {

    public static void main(String[] args) {

        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("localhost", 10004));
            socket.getOutputStream().write("hello i am client v5".getBytes());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("client v5 finish ... ");

    }

}
