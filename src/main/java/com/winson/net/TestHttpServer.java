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

        ServerSocket serverSocket = new ServerSocket(8000);
        boolean quit = false;
        while (!quit){
            Socket socket = serverSocket.accept();
            System.out.println("create connect ... ");

//            InputStream in = socket.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//            StringBuffer sb = new StringBuffer();
//            String temp = null;
//            while (true){
//                temp = reader.readLine();
//                if(temp == null || temp.isEmpty()){
//                    break;
//                }
//                sb.append(temp);
//                sb.append("\r\n");
//            }
//            System.out.println(sb.toString());

            String response = "HTTP/1.1 200 ok\n\nHello World\n";
//            socket.getOutputStream().write(response.getBytes());
//            socket.getOutputStream().flush();
            System.out.println("send response ");
            BufferedWriter br = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            br.write(response);
            br.flush();

            socket.close();
        }


        System.out.println("http server stop !");

    }

}
