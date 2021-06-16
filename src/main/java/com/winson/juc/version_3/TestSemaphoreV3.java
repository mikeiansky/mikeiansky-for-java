package com.winson.juc.version_3;

import java.util.concurrent.Semaphore;

/**
 * @author winson
 * @date 2021/6/16
 **/
public class TestSemaphoreV3 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("test semaphore version 3.0 start ... ");
        Semaphore semaphore = new Semaphore(3);
        int size = 10;
        Thread[] threads = new Thread[size];
        for (int i = 0; i < size; i++) {
            final int flag = i;
            threads[i] = new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("Thread " + flag + " start ... ");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
                System.out.println("Thread " + flag + " end ... ");
            });
            threads[i].start();
        }

        for (int i = 0; i < size; i++) {
            threads[i].join();
        }
        System.out.println("test semaphore version 3.0 stop ... ");
    }

}
