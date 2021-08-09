package com.winson.protocol.http;

import org.fusesource.hawtbuf.BufferInputStream;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author winson
 * @date 2021/8/9
 * @desc http协议测试类，v1版本
 **/
public class HttpServerDemoV1 {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8001);
            listen(serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void listen(ServerSocket serverSocket) {
        try {
            for (; ; ) {
                Socket socket = serverSocket.accept();
                handleSocket(socket);
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleSocket(Socket socket) {
        try {
            System.out.println("handle socket ============> start ");
            InputStream in = socket.getInputStream();

//            int length = 1024;
//            byte[] buf = new byte[length];
//            while ( (length = in.read(buf,0, 1024))!=-1){
//                System.out.println("read length : " + length);
//                String result = new String(buf, 0 , length);
//                System.out.println(result);
//            }
//
//
            String line = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if(line.equals("\r\n") || line.trim().equals("")){
//                    System.out.println("回车换行，准备下一个接收数据");
                    break;
                }
            }

            String result = "HTTP/1.1 200 OK \n\n";
            result += "hello,world!";

            socket.getOutputStream().write(result.getBytes());

            System.out.println("handle socket ============> end ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
