package com.winson.jdkapi.io.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;

/**
 * @author winson
 * @date 2021/8/26
 **/
public class NioEchoClientDemoV1 {

    public static void main(String[] args) {

        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("localhost", 10001));

            while (true) {
                byte[] buf = new byte[1024];
                int writeLength = System.in.read(buf);
                socket.getOutputStream().write(buf, 0, writeLength);
                byte[] readBuf = new byte[1024];
                int readLength = socket.getInputStream().read(readBuf);
                String readResult = new String(readBuf, 0 , readLength);
                System.out.println("echo message : " + readResult);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("nio echo client demo v1 finish ... ");

    }

}
