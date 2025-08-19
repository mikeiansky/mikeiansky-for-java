package io.github.mikeiansky.java.base.jdk.reference;

import java.lang.ref.WeakReference;

public class WeakReferenceDemo {

    public static class MyWeek extends WeakReference<Object> {

        public MyWeek(Object referent) {
            super(referent);
        }

    }

    public static void main(String[] args) {
        Object obj = new Object();
        System.out.println("before weak reference : " + obj);
        WeakReference<Object> weakReference = new  WeakReference<>(obj);
        System.out.println("after weak reference : " + weakReference.get());

    }

}
