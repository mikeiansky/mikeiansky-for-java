package com.winson.jdkapi.juc.base;

import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2022/6/17
 **/
public class SameNameThreadDemoV1 {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        Thread t1 = new Thread(() -> {
            System.out.println("thread1 start ... ");
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "winson-001");

        Thread t2 = new Thread(() -> {
            System.out.println("thread2 start ... ");
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "winson-001");

        t1.start();
        t2.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("main complete ... ");
    }

}
