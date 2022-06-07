package com.winson.jdkapi.juc.v2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * @author winson
 * @date 2022/5/11
 **/
public class SemaphoreDemoV1 {

    static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        Semaphore semaphore = new Semaphore(1);
        int size = 100000;
        final int each = 100000;
        final int tss = 10;
        final CountDownLatch latch = new CountDownLatch(tss);
        final Thread[] ts = new Thread[tss];
        for (int i = 0; i < tss; i++) {
            ts[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        for (int j = 0; j < each; j++) {
                            count++;
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    } finally {
                        semaphore.release();
                    }
                    latch.countDown();
                }
            });
            ts[i].start();
        }
//        for (int i = 0; i < tss; i++) {
//            ts[i].join();
//        }

        latch.await();

        System.out.println("count : " + count);

    }

}
