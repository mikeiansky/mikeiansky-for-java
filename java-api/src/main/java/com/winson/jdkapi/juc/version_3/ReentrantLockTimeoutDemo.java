package com.winson.jdkapi.juc.version_3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author winson
 * @date 2021/6/21
 **/
public class ReentrantLockTimeoutDemo {

    private ReentrantLock lock = new ReentrantLock();

    public void enter(String source){
        lock.lock();
        try {
            System.out.println(source + " , Thread isInterrupted : " + Thread.currentThread().isInterrupted());
            System.out.println(source + " , Thread isAlive : " + Thread.currentThread().isAlive());
            System.out.println(source + " enter lock ... ");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReentrantLockTimeoutDemo demo = new ReentrantLockTimeoutDemo();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 start ... ");
                demo.enter("t1");
                System.out.println("t1 end ... ");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 start ... ");
                demo.enter("t2");
                System.out.println("t2 end ... ");
            }
        });



        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t3 start ... ");
                t2.interrupt();
                System.out.println("t3 end ... ");
            }
        });

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
