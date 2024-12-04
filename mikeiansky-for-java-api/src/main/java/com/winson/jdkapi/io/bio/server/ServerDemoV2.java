package com.winson.jdkapi.io.bio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author winson
 * @date 2021/8/20
 **/
public class ServerDemoV2 {

    public static void main(String[] args) {
        ArrayList<Socket> sockets = new ArrayList<>();
        try {
            int backlog = 2;
            ServerSocket serverSocket = new ServerSocket();
            System.out.println("server socket state , is bind : " + serverSocket.isBound() + " , is closed : " + serverSocket.isClosed());
            serverSocket.bind(new InetSocketAddress("localhost", 10002), backlog);
            System.out.println("server socket state , is bind : " + serverSocket.isBound() + " , is closed : " + serverSocket.isClosed());

            System.out.println("server socket local port : " + serverSocket.getLocalPort());
            System.out.println("server socket address : " + serverSocket.getInetAddress());

            AtomicLong count = new AtomicLong();

            while (true){
//                Socket socket = serverSocket.accept();
//                System.out.println("receive socket : " + socket);
//                System.out.println("receive socket size : " + count.incrementAndGet());
//                sockets.add(socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("server demo v2 finish ... ");


    }

}
