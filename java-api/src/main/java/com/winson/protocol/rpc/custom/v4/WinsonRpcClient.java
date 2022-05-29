package com.winson.protocol.rpc.custom.v4;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author winson
 * @date 2022/5/29
 **/
public class WinsonRpcClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        final int size = 1;
        final int msgCount = 1;
        Thread[] ts = new Thread[size];
        for (int i = 0; i < size; i++) {
            ts[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Socket socket = new Socket();
                        socket.connect(new InetSocketAddress("localhost", 10001));
                        OutputStream out = socket.getOutputStream();
                        for (int i = 0; i < msgCount; i++) {
                            out.write(String.format("hello-%04d", i + 1).getBytes(StandardCharsets.UTF_8));
                        }
                        out.flush();
                        Thread.sleep(1000);
                        socket.close();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            ts[i].start();
        }

        for (int i = 0; i < size; i++) {
            ts[i].join();
        }

        System.out.println("client end ... ");
    }

}
