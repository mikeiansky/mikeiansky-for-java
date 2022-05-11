package com.winson.advanced.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author winson
 * @date 2022/5/11
 **/
public class CyclicBarrierDemoV1 {

    static boolean run = true;

    public static void main(String[] args) {

        int size = 10;
        CountDownLatch latch = new CountDownLatch(10);

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("run action on cyclic barrier !" + Thread.currentThread());
                latch.countDown();
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (run) {
                    try {
                        System.out.println("Things one ready !");
                        Thread.sleep(500);
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (run) {
                    try {
                        System.out.println("Things two ready !");
                        Thread.sleep(1000);
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();


        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        run = false;
        System.out.println("main end ... ");
    }

}
