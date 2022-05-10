package com.winson.advanced.concurrency.lock;

import org.apache.regexp.RE;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author winson
 * @date 2022/5/10
 **/
public class LockConditionDemoV1 {

    public static void main(String[] args) {

        final ReentrantLock lock = new ReentrantLock();
        final CountDownLatch latch = new CountDownLatch(2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread1 start ");
                boolean getLock = lock.tryLock();
                System.out.println("thread1 get lock : " + getLock);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                latch.countDown();
                lock.unlock();
                System.out.println("Thread1 end ");
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread2 start ");
                boolean getLock = lock.tryLock();
                System.out.println("thread2 get lock : " + getLock);
                latch.countDown();
                if(getLock){
                    lock.unlock();
                }
                System.out.println("Thread2 end ");
            }
        }).start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("main end ...");

    }

}
