package com.winson.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author winson
 * @date 2021/5/6
 **/
public class TestHttpServer {

    public static void main(String[] args) throws IOException {

        System.out.println("http server start ... ");

        ServerSocket serverSocket = new ServerSocket(19999);
        boolean quit = false;
        while (!quit){
            Socket socket = serverSocket.accept();
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuffer sb = new StringBuffer();
            String temp = null;
            while ((temp = reader.readLine() )!=null){
                sb.append(temp);
                sb.append("\r\n");
            }
            System.out.println(sb.toString());

            String response = "HTTP/1.1 200 OK";
            socket.getOutputStream().write(response.getBytes());
            socket.getOutputStream().flush();

            socket.close();
        }


        System.out.println("http server stop !");

    }

}
