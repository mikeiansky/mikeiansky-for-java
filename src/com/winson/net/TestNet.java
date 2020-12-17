package com.winson.net;

import java.net.InetAddress;

/**
 * @author winson
 * @date 2020/12/17
 **/
public class TestNet {

    public static void main(String[] args) {
        System.out.println("test net start");
        try {
            InetAddress address = InetAddress.getLocalHost();
            String ip = address.getHostAddress();
            System.out.println("test net ip:" + ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("test net start");
    }

}
