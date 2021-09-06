package com.winson.jdkapi.io.bio.client;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2021/8/20
 **/
public class ClientDemoV2 {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        try {
            Socket socket = new Socket();
//            socket.setSendBufferSize(10000);
            socket.setTcpNoDelay(true);
//            socket.setSoTimeout(5000);
//            socket.setSoLinger(true, 10);
//            socket.setOOBInline(true);
            socket.connect(new InetSocketAddress("localhost", 10001));

            BufferedReader realReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            InputStream in = System.in;
            OutputStream out = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null){
                System.out.println("send message : " + line);
                String msg = line + "\r\n";
                out.write(msg.getBytes());

                String response = realReader.readLine();
                System.out.println("server response : " + response);
                if(response.equals("quit")){
                    socket.close();
                    break;
                }

            }
            System.out.println("client demo v2 socket close 1 ... ");

            line = reader.readLine();
            String msg = line + "\r\n";
            out.write(msg.getBytes());

            System.out.println("client demo v2 socket close 2 ... ");
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("client demo v2 finish ... ");

    }

}
