package com.winson.temp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author winson
 * @date 2021/8/23
 **/
public class LinuxClientDemoV1 {

    public static void main(String[] args) {
        AtomicLong count = new AtomicLong();
        Socket socket = new Socket();
        try {
            socket.bind(new InetSocketAddress("192.168.159.1", 10002));
            socket.setSendBufferSize(10);
            socket.connect(new InetSocketAddress("192.168.159.130", 20001));
            System.out.println("connect server success ... ");
            System.in.read();
            while (true){
                byte[] buf = new byte[1024];
                int readLength = System.in.read(buf);
                for (int i = 0; i < readLength; i++) {
                    count.incrementAndGet();
                    socket.getOutputStream().write(buf[i]);
                }
                System.out.println("当前已经发送数据长度：" + count.get());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("client demo finish ");

    }

}
