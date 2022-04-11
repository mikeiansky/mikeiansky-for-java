package com.winson.jdkapi.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author winson
 * @date 2022/4/11
 **/
public class SocketDemoV1 {

    public static void main(String[] args) {

        Socket socket = new Socket();
        InetSocketAddress address = new InetSocketAddress("tech.ciwei", 22);
//        InetSocketAddress address = new InetSocketAddress("172.16.2.211", 5000);
        try {
            socket.connect(address);
            System.out.println("connect success ... ");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("connect fail ... ");
        }

    }

}
