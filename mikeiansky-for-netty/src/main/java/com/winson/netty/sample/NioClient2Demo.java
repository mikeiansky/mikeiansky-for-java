package com.winson.netty.sample;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author mike ian
 * @date 2024/12/24
 * @desc
 **/
public class NioClient2Demo {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1", 9090));
        System.out.println("client connect server success ");
        boolean start = true;
        while (start) {
            int value = System.in.read();
            System.out.println("send value is " + value);
            socket.getOutputStream().write(value);
            if (value == 0) {
                start = false;
            }
        }
        socket.close();

        System.out.println("client complete ... ");

    }

}
