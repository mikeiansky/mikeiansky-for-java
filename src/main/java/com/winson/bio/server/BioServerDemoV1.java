package com.winson.bio.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author winson
 * @date 2021/8/17
 **/
public class BioServerDemoV1 {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(20001);

            while (true) {
                Socket socket = serverSocket.accept();
                socket.setKeepAlive(true);
                System.out.println("accept socket : " + socket);
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("socket is closed : " + socket.isConnected());

//                        if(socket.isClosed()){
//                            System.out.println("socket is closed : " + socket.isClosed());
//                        } else {
//                            System.out.println("socket is activing : " + socket.isClosed());
//                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(" bio server demo v1 finish ... ");

    }

}
