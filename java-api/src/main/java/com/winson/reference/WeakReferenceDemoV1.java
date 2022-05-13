package com.winson.reference;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * @author winson
 * @date 2022/5/10
 **/
public class WeakReferenceDemoV1 {

    //-XX:+UseSerialGC -XX:+PrintGCDetails -Xmx10m -Xms2m -Xmn2m
    public static void main(String[] args) {
        System.out.println("weak reference demo start");
        ArrayList<byte[]> record = new ArrayList<>();
        byte[] buf1 = new byte[1024 * 1024];
        System.out.println("one");
        // 方式1
//        WeakReference<byte[]> wr = new WeakReference<>(new byte[1024* 1024]);
        WeakReference<byte[]> wr = new WeakReference<>(buf1);
        System.out.println("buf1 : " + buf1);
        buf1 = null;
        System.out.println("wr.get after one : "+wr.get());
        byte[] buf2 = new byte[1024*1024];
        record.add(buf2);
        System.out.println("wr.get after two : "+wr.get());
        byte[] buf3 = new byte[1024*1024];
        record.add(buf3);
        System.out.println("wr.get after three : "+wr.get());
        byte[] buf4 = new byte[1024*1024];
        record.add(buf4);
        System.out.println("wr.get after four : "+wr.get());
        byte[] buf5 = new byte[1024*1024];
        record.add(buf5);
        System.out.println("wr.get after five : "+wr.get());
        byte[] buf6 = new byte[1024*1024];
        record.add(buf6);
        byte[] buf7 = new byte[1024*1024];
        record.add(buf7);
        byte[] buf8 = new byte[1024*1024];
        record.add(buf8);
        System.out.println("wr.get after end : "+wr.get());

        System.out.println("weak reference demo end");
    }

}
