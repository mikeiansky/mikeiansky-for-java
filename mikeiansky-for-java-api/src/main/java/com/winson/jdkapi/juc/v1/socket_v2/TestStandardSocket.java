package com.winson.jdkapi.juc.v1.socket_v2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author winson
 * @date 2021/5/17
 **/
public class TestStandardSocket {

    public static void main(String[] args) throws IOException {
        System.out.println("test standard socket start ... ");

        ServerSocket serverSocket = new ServerSocket(8888);
        boolean running = true;
        while (running) {
            Socket socket = serverSocket.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = br.readLine();
            StringBuilder request = new StringBuilder();
            while (line != null && !line.trim().equals("")) {
                request.append(line);
                request.append("\n");
                line = br.readLine();
            }


            System.out.println("request==========>");
            System.out.println(request);


            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write("HTTP/1.1 200 ok\n\nHello World\n");
            writer.flush();
            socket.close();


        }


        System.out.println("test standard socket end ... ");
    }

}
