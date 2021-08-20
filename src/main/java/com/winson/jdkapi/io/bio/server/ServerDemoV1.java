package com.winson.jdkapi.io.bio.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2021/8/20
 **/
public class ServerDemoV1 {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1);

        try {
            int backlog = 0;
            ServerSocket serverSocket = new ServerSocket(10001, backlog);

            System.out.println("server start success ... : " + serverSocket.getLocalPort());

//            while (true) {
//                Socket socket = serverSocket.accept();
//                System.out.println("accept socket : " + socket);
//
//            }

            Socket socket = serverSocket.accept();

            System.out.println("client connect socket : " + socket);
            InputStream sin = System.in;
            BufferedReader inReader = new BufferedReader(new InputStreamReader(sin));

            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null){
                System.out.println("receive message : " + line);
//                if(line.equals("quit")){
//                    in.close();
//                    socket.close();
//                    serverSocket.close();
//                    break;
//                }
                String response = inReader.readLine() + "\r\n";
                socket.getOutputStream().write(response.getBytes());

                if(response.startsWith("quit")){
                    break;
                }

            }

            System.out.println("socket client quit");

            while ((line = inReader.readLine())!=null){
                System.out.println("server send message : " + line);
                String msg = line + "\r\n";
                socket.getOutputStream().write(msg.getBytes());
            }

            System.out.println("server socket close ... ");

            latch.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("server demo v1 finish ... ");
    }

}
