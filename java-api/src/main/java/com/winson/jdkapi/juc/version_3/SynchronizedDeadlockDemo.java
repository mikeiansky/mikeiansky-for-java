package com.winson.jdkapi.juc.version_3;

/**
 * @author winson
 * @date 2021/6/18
 **/
public class SynchronizedDeadlockDemo {



    public static void main(String[] args) throws InterruptedException {
        System.out.println("synchronized deadlock demo start ... ");

        final Object lockA = new Object();
        final Object lockB = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 thread start ... ");
                synchronized(lockA){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lockB){
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("t1 thread end ... ");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2 thread start ... ");
                synchronized (lockB){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lockA){
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("t2 thread end ... ");
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();


        System.out.println("synchronized deadlock demo end ... ");
    }

}
