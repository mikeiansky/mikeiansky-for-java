package com.winson.jvm.classloader;

import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2021/9/1
 * @desc 多线程时候加载同一个类，看下加载的线程在哪里
 **/
public class MultiThreadLoadClassDemoV1 {

    public static class LoadTemp1{
        static {
            System.out.println("load temp 1 initial @ thread :: " + Thread.currentThread().getName());
        }
    }

    public static class LoadTemp2{
        static {
            System.out.println("load temp 2 initial @ thread :: " + Thread.currentThread().getName());
        }
    }

    public static class LoadTemp3{
        static {
            System.out.println("load temp 3 initial @ thread :: " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " ----- start");
        LoadTemp1 loadTemp1 = new LoadTemp1();
        CountDownLatch latch = new CountDownLatch(3);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " ----- start");
            LoadTemp2 loadTemp2 = new LoadTemp2();
            latch.countDown();
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " ----- start");
            LoadTemp3 loadTemp3 = new LoadTemp3();
            latch.countDown();
        }).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " ----- start");
            LoadTemp3 loadTemp3 = new LoadTemp3();
            latch.countDown();
        }).start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
