package com.winson.jdkapi.juc.v1.version_3;

/**
 * @author winson
 * @date 2021/6/30
 **/
public class ThreadLocalDemo {

    public static void main(String[] args) {

        ThreadLocal<String> tl1 = new ThreadLocal<>();
        tl1.set("winson");
        System.out.println("before 32 object set !");
        System.out.println(tl1.get());

        int size = 32;
        ThreadLocal<String>[] tls = new ThreadLocal[size];
        for (int i = 0; i < size; i++) {
            tls[i] = new ThreadLocal<>();
            tls[i].set("tls - " + i);
        }

        System.out.println("after 32 object set !");
        System.out.println(tl1.get());

    }

}
