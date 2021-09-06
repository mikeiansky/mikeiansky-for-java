package com.winson.jvm.gc;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author winson
 * @date 2021/6/28
 **/
public class WeakReferenceGCDemo {

    static class NormalObject{

        @Override
        protected void finalize() throws Throwable {
            System.out.println("normal object finalize ---> ");
        }
    }

    static class SoftObject{
        @Override
        protected void finalize() throws Throwable {
            System.out.println("soft object finalize ---> ");
        }
    }

    static class WeakObject{

        @Override
        protected void finalize() throws Throwable {
            System.out.println("weak object finalize ---> ");
        }
    }

    public static void main(String[] args) {
        NormalObject normalObject = new NormalObject();
//        SoftObject softObject = ;
        SoftReference softObject = new SoftReference<>(new SoftObject());
//        WeakObject weakObject = new WeakObject();
//        WeakReference<WeakObject> weakObject = new WeakReference<>(new WeakObject());
//        WeakObject weakObject = new WeakObject();
        WeakReference weakObject = new WeakReference<>(new WeakObject());
        System.out.println("before gc ---------- ");
        System.out.println("weakObject.get() ---------- : " +weakObject.get());
        System.gc();
        System.out.println("after gc ---------- ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("weakObject ---------- : " +weakObject);
        System.out.println("weakObject.get() ---------- : " +weakObject.get());

        System.out.println("main end ...");

    }

}
