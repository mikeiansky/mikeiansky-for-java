package com.winson.jdkapi.juc.version_1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author winson
 * @date 2020/12/29
 * @desc 多线程死锁，使用ReentrantLock方式
 **/
public class TestDeadLockV2_UseReentrantLock {

    public static void main(String[] args) throws InterruptedException {

        final ReentrantLock lockOne = new ReentrantLock();
        final ReentrantLock lockTwo = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            System.out.println("thread1 start");
            System.out.println("thread1 ready get lock one .. ");
            lockOne.lock();
            System.out.println("thread1 get lock one .. ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread1 ready get lock two .. ");
            lockTwo.lock();
            System.out.println("thread1 get lock two .. ");

            lockTwo.unlock();
            lockOne.unlock();
            System.out.println("thread1 end .. ");
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            System.out.println("thread2 start");
            System.out.println("thread2 ready get lock one .. ");
            lockTwo.lock();
            System.out.println("thread2 get lock one .. ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread2 ready get lock two .. ");
            lockOne.lock();
            System.out.println("thread2 get lock two .. ");

            lockOne.unlock();
            lockTwo.unlock();
            System.out.println("thread2 end .. ");
        });
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("main end ... ");
    }

}
