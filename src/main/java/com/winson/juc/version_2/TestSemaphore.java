package com.winson.juc.version_2;

import java.util.concurrent.Semaphore;

/**
 * @author winson
 * @date 2021/5/8
 **/
public class TestSemaphore {

    public static void main(String[] args) {
        System.out.println("test semaphore start ... ");

        Semaphore semaphore = new Semaphore(5);

        Thread[] ts = new Thread[100];
        for (int i = 0; i < ts.length; i++) {
            final int index = i;
            ts[i] = new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("ts index : " + index);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
            ts[i].start();
        }

        try {
            for (int i = 0; i < ts.length; i++) {
                ts[i].join();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("test semaphore end ...");
    }

}
