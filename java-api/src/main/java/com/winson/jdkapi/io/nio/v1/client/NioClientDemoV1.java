package com.winson.jdkapi.io.nio.v1.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author winson
 * @date 2021/8/17
 **/
public class NioClientDemoV1 {

    public static void main(String[] args) {

        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("localhost", 10002));
            System.out.println("connect success");

            socket.getOutputStream().write("winson1".getBytes());
            Thread.sleep(5000);
            socket.getOutputStream().write("winson2".getBytes());
            socket.getOutputStream().write("quit".getBytes());
            socket.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
