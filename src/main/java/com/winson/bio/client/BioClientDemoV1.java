package com.winson.bio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author winson
 * @date 2021/8/17
 **/
public class BioClientDemoV1 {

    public static void main(String[] args) {

        Socket socket = new Socket();
        try {
            socket.setKeepAlive(true);
            socket.connect(new InetSocketAddress("localhost", 20001));
            System.out.println("socket connect success : " + socket);
//            socket.getInputStream().close();
//            socket.getOutputStream().close();
            System.out.println("socket disconnect : " + socket.isClosed());
            socket.close();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("socket disconnect : " + socket.isClosed());
            System.out.println("socket disconnect : " + socket.isConnected());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
