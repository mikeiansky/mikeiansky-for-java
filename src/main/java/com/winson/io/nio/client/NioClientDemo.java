package com.winson.io.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author winson
 * @date 2021/8/17
 **/
public class NioClientDemo {

    public static void main(String[] args) {

        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("localhost", 10004));
            socket.getOutputStream().write("this is normal client".getBytes());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("normal client finish ... ");

    }

}
