package com.winson.advanced.concurrency.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author winson
 * @date 2022/5/13
 **/
public class LockSignalDemoV1 {

    static boolean end = false;

    public static void main(String[] args) throws InterruptedException {
        final ReentrantLock lock = new ReentrantLock();
        Condition done = lock.newCondition();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread 1 start ");
                lock.lock();
                while (!end) {
                    try {
                        done.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("done signal and run ... ");
                }
                lock.unlock();
                System.out.println("thread 1 end ");
            }
        });
        t1.start();

        Thread.sleep(1000);

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread 2 start ");
                lock.lock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                done.signalAll();
                end = true;
                System.out.println("t2 signall ");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("t2 unsleep and run ... ");
                lock.unlock();
                System.out.println("thread 2 end ");
            }
        });
        t2.start();

        t1.join();
        t2.join();
        System.out.println("main end ");
    }

}
