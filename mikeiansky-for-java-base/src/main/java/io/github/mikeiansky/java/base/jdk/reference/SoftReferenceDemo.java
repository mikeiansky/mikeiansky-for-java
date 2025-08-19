package io.github.mikeiansky.java.base.jdk.reference;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {

    public static void main(String[] args) {
        Object obj = new Object();
        System.out.println("before soft reference : " + obj);
        SoftReference<Object> softReference = new SoftReference<>(obj);
        System.out.println("after soft reference : " + softReference.get());
    }

}
