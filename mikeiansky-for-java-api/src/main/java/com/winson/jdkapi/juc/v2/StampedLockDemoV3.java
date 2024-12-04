package com.winson.jdkapi.juc.v2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.StampedLock;

/**
 * @author winson
 * @date 2022/5/11
 * @desc intercept
 **/
public class StampedLockDemoV3 {

    StampedLock lock = new StampedLock();


    public static void main(String[] args) throws InterruptedException {
        StampedLock lock = new StampedLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 start ");
                long st = lock.writeLock();
                CountDownLatch latch = new CountDownLatch(1);
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                lock.unlock(st);
                System.out.println("t1 end ");
            }
        });
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2 start ");
                long st = lock.readLock();
                System.out.println("t2. read data !");
                lock.unlock(st);
                System.out.println("t2 end ");
            }
        });
        t2.start();

        t2.interrupt();
        t1.join();

        System.out.println("main end ... ");
    }

}
