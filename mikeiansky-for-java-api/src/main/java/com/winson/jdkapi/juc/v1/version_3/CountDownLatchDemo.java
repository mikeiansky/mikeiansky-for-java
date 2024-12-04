package com.winson.jdkapi.juc.v1.version_3;

import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2021/6/21
 **/
public class CountDownLatchDemo {

    public static void main(String[] args) {

        int size = 10;
        CountDownLatch countDownLatch = new CountDownLatch(size);

        Thread[] threads = new Thread[size];
        for (int i = 0; i < size; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countDownLatch.countDown();
                }
            });
            threads[i].start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All things are ready!");

    }

}
