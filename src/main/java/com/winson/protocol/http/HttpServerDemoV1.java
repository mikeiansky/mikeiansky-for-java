package com.winson.protocol.http;

import org.fusesource.hawtbuf.BufferInputStream;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author winson
 * @date 2021/8/9
 * @desc http协议测试类，v1版本
 **/
public class HttpServerDemoV1 {

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
//            System.out.println("in . available : " + in.available() );

            byte[] readBuf = new byte[1024];
            int readLength = in.read(readBuf);
            System.out.println("readLength : " + readLength);
            String request = new String(readBuf, Charset.forName("gbk"));
            System.out.println(request);
            System.out.println("read --------------> end");

//            String line = null;
//            StringBuilder sb = new StringBuilder();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//            int lineNumber = 0;
//            Map<String,String> headers = new HashMap<>();
//            while ((line = reader.readLine()) != null) {
//                lineNumber++;
//                if(!line.equals("") && lineNumber > 1){
//                    String[] header = line.split(":");
//                    headers.put(header[0].trim(), header[1].trim());
//                }
////                System.out.println("'---->"+line+"<----'");
//                if(line.equals("\r\n") || line.trim().equals("")){
////                    System.out.println(" end with ascii");
//                    sb.append(line);
//                    break;
//                }else{
//                    sb.append(line+"\r\n");
//                }
//            }
//            System.out.println(sb);

//            String contentLength = headers.get("Content-Length");
//            if(contentLength!=null){
//                int cv = Integer.parseInt(contentLength);
//                System.out.println("Content-Length:" + cv);
//                // get body
//                byte[] buf = new byte[1];
//                System.out.println("read body start");
////                in.skip(sb.toString().getBytes().length);
////                in.read(buf, 0, 1);
//
//                int bodyLength = 0;
//                StringBuilder body = new StringBuilder();
//                while ((line = reader.readLine())!=null){
//                    line = line+"\r\n";
//                    body.append(line);
//                    System.out.println(line);
//                    bodyLength += line.getBytes().length;
//                    System.out.println("bodyLength : " + bodyLength + " , cv : " + cv);
//                    if(bodyLength>= cv-4){
//                        break;
//                    }
//                }
//
//                System.out.println("body : " + body);
//
//                System.out.println("read body end");
//                System.out.println(new String(buf,0, 1));
//            }

            System.out.println("request end ... ");

            String result = "HTTP/1.1 200 OK \n";
            result += "Accept:application/json;charset=gbk\n\n";
            result += "hello,world!\r\n我不好你好世界";
            byte[] buf = result.getBytes(Charset.forName("gbk"));
//            byte[] buf = result.getBytes();
            socket.getOutputStream().write(buf);
//            System.out.println(new String(buf, Charset.forName("gbk")));

            System.out.println("handle socket ============> end ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

}
