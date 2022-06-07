package com.winson.jdkapi.juc.v1.version_1;

/**
 * @author winson
 * @date 2020/12/29
 * @desc 死锁v1版本，使用synchronized方式
 **/
public class TestDeadLockV1_UseSynchroinzed {

    public static void main(String[] args) throws InterruptedException {

        final Object lockOne = new Object();
        final Object lockTwo = new Object();

        Thread print = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("print start ... ");
                boolean flag = true;
                int index = 0;
                while (flag){
                    index = 2;
                }
                System.out.println("print end ... ");
            }
        });
        print.setName("winson-thread");
        print.start();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1 start.");
                System.out.println("thread1 ready get lock one");
                synchronized (lockOne){
                    System.out.println("thread1 get lock one");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread1 ready get lock two");
                    synchronized (lockTwo){
                        System.out.println("thread1 get lock two");

                    }
                }
                System.out.println("thread1 end.");
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread2 start.");
                System.out.println("thread2 ready get lock two");
                synchronized (lockTwo){
                    System.out.println("thread2 get lock two");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread2 ready get lock one");
                    synchronized (lockOne){
                        System.out.println("thread2 get lock one");

                    }
                }
                System.out.println("thread2 end.");
            }
        });
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println("main end.");
    }



}
