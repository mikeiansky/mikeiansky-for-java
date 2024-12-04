package com.winson.jvm.common;

import java.net.InetSocketAddress;

/**
 * @author winson
 * @date 2021/7/4
 **/
public class TestHelloWorld {

    public static void main(String[] args) {
//        final int b = 20;
//        System.out.println("Hello World!");
//        int a = b;
//        System.out.println(a);
//        System.out.println(b);
//        new Runnable(){
//
//            @Override
//            public void run() {
//
//            }
//        };
        InetSocketAddress a1 = new InetSocketAddress("localhost", 9999);
        InetSocketAddress a2 = new InetSocketAddress("localhost", 9999);
        System.out.println(a1.equals(a2));
    }

}
