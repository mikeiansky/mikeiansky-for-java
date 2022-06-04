package com.winson.jdkapi.io.nio.v1.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author winson
 * @date 2021/8/17
 **/
public class NioClientDemoV5 {

    public static void main(String[] args) {

        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("localhost", 10004));
            socket.getOutputStream().write("hello i am client v5 msg 1".getBytes());

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            socket.getOutputStream().write("hello i am client v5 msg 2".getBytes());

            InputStream in = socket.getInputStream();
            byte[] buf = new byte[1024];
            int length = in.read(buf);
            if(length>0){
                String readResult = new String(buf);
                System.out.println("read result : " + readResult);
            } else {
                System.out.println("nothing ");
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("client v5 finish ... ");

    }

}
