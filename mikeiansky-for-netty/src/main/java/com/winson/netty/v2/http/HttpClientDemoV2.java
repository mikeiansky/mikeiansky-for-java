package com.winson.netty.v2.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author mike ian
 * @date 2025/6/5
 * @desc
 **/
public class HttpClientDemoV2 {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket();
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 60006);
        socket.connect(address);

        // 测试拆包发送
        System.out.println("ready split send ... ");
        OutputStream out = socket.getOutputStream();
        String getRequestAll = "GET /hello HTTP/1.1\r\n\r\n";
        System.out.println("send request one");
        String getRequest1 = "GET /hel";
        out.write(getRequest1.getBytes());
        out.flush();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        in.readLine();
        System.out.println("send request two");
        String getRequest2 = "lo HTTP/1.1\r\n\r\n";
        out.write(getRequest2.getBytes());
        out.flush();

        // 测试粘包发送
        in.readLine();
        System.out.println("ready send gather two request ... ");
        String getRequestTwo = "GET /second HTTP/1.1\r\n\r\nGET /second HTTP/1.1\r\n\r\n";
        out.write(getRequestTwo.getBytes());
        out.flush();

        in.readLine();
        out.close();
        socket.close();

        System.out.println("client complete ... ");
    }

}
