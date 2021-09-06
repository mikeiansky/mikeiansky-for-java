package com.winson.jvm.gc;

/**
 * @author winson
 * @date 2021/6/28
 **/
public class ReferenceCountingGCDemo {

    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    private byte[] bigSize = new byte[2 * _1MB];

    // -XX:+PrintGC
    // -XX:+PrintGCDetails
    public static void main(String[] args) {

        ReferenceCountingGCDemo obj1 = new ReferenceCountingGCDemo();
        ReferenceCountingGCDemo obj2 = new ReferenceCountingGCDemo();
        obj1.instance = obj2;
        obj2.instance = obj1;

        obj1 = null;
        obj2 = null;

        System.gc();

    }

}
