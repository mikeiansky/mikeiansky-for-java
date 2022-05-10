package com.winson.reference;

import sun.misc.VM;

import java.lang.ref.SoftReference;

/**
 * @author winson
 * @date 2022/5/10
 **/
public class SoftReferenceDemoV1 {

    //-XX:+UseSerialGC -XX:+PrintGCDetails -Xmx10m -Xms2m -Xmn2m
    public static void main(String[] args) {
        System.out.println("soft reference demo v1 start ");
        System.out.println("one");
        byte[] buf1 = new byte[1024*1024];
        SoftReference<byte[]> s1 = new SoftReference<>(new byte[1024*512]);
        System.out.println("after one:"+s1.get());
        System.out.println("two");
        byte[] buf2 = new byte[1024*1024*5];
        System.out.println("after two:"+s1.get());
        System.out.println(s1);
//        System.out.println(System.getProperty("FileDir"));
        System.out.println("soft reference demo v1 end ");

        System.out.println(VM.getSavedProperty("java.lang.Integer.IntegerCache.high"));
        System.out.println(VM.getSavedProperty("-XX:+UseSerialGC"));
    }

}
