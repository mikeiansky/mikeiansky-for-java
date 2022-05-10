package com.winson.reference;

import java.lang.ref.WeakReference;

/**
 * @author winson
 * @date 2022/5/10
 **/
public class WeakReferenceDemoV1 {

    //-XX:+UseSerialGC -XX:+PrintGCDetails -Xmx10m -Xms2m -Xmn2m
    public static void main(String[] args) {
        System.out.println("weak reference demo start");

        byte[] buf1 = new byte[1024 * 1024];
        System.out.println("one");
        WeakReference<byte[]> wr = new WeakReference<>(new byte[1024* 1024]);
        System.out.println("wr.get after one : "+wr.get());
        byte[] buf2 = new byte[1024*1024];
        System.out.println("wr.get after two : "+wr.get());
        byte[] buf3 = new byte[1024*1024];
        System.out.println("wr.get after three : "+wr.get());

        System.out.println("weak reference demo end");
    }

}
