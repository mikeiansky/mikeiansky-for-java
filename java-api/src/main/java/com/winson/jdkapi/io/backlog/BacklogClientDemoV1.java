package com.winson.jdkapi.io.backlog;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author winson
 * @date 2022/6/8
 **/
public class BacklogClientDemoV1 {

    public static void main(String[] args) {

        try {
            Socket socket1 = new Socket();
            socket1.connect(new InetSocketAddress("localhost", 10002));
            System.out.println("socket1 connect success");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Socket socket2 = new Socket();
            socket2.connect(new InetSocketAddress("localhost", 10002));
            System.out.println("socket2 connect success");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Socket socket3 = new Socket();
            socket3.connect(new InetSocketAddress("localhost", 10002));
            System.out.println("socket3 connect success");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Socket socket4 = new Socket();
            socket4.connect(new InetSocketAddress("localhost", 10002));
            System.out.println("socket4 connect success");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
