package com.winson.jdkapi.juc.v3;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Mike Ian
 * @date 2023/4/16
 **/
public class ReadWriteLockDemoV3 {

    private static int readCount = 0;

    public static void write(ReentrantReadWriteLock.WriteLock writeLock, ReentrantReadWriteLock.ReadLock readLock){
        writeLock.lock();

        System.out.println("do write ... ");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("do write ... complete");


        writeLock.unlock();
    }

    public static void read(ReentrantReadWriteLock.WriteLock writeLock, ReentrantReadWriteLock.ReadLock readLock){
//        System.out.println("read thread start ... ");
//        writeLock.lock();
//        System.out.println("read thread get write lock");
//        readCount++;
//        if (readCount == 1) {
//            readLock.lock();
//        }
//        writeLock.unlock();
        readLock.lock();

        System.out.println("do read ... ");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("do read ... complete ");

        readLock.unlock();

//        writeLock.lock();
//        readCount--;
//        if (readCount == 0) {
//            readLock.unlock();
//        }
//        writeLock.unlock();
    }

    public static void main(String[] args) {

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                write(writeLock, readLock);
            }).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                read(writeLock, readLock);
            }).start();
        }


//        new Thread(()->{
//            try {
//                writeLock.lock();
//                System.out.println("thread 1 start ---------> ");
//                Thread.sleep(1000);
//                System.out.println("thread 1 complete ---------> ");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                writeLock.unlock();
//            }
//        }).start();
//
//        new Thread(()->{
//            try {
//                writeLock.lock();
//                System.out.println("thread 2 start ---------> ");
//                Thread.sleep(2000);
//                System.out.println("thread 2 complete ---------> ");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                writeLock.unlock();
//            }
//        }).start();

    }

}
