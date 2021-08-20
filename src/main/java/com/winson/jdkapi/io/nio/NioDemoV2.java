package com.winson.jdkapi.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * @author winson
 * @date 2021/8/17
 **/
public class NioDemoV2 {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(10000));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
