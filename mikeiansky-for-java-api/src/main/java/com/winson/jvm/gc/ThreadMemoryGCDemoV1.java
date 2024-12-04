package com.winson.jvm.gc;

import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2021/9/7
 **/
public class ThreadMemoryGCDemoV1 {

    /**
     * -XX:+PrintHeapAtGC
     * VM参数：-XX:+UseSerialGC -XX:+PrintGC -XX:+PrintGCDetails -Xms20M -Xmx20M -Xmn20M -Xss2M
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        CountDownLatch latch = new CountDownLatch(10);

        int size = 100;
        for (int i = 0; i < size; i++) {
            final int finalIndex = i;
            new Thread(() -> {
                System.out.println("thread " + finalIndex + " start ");
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread " + finalIndex + " end ");
            }).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("app finish ... ");
    }

}
