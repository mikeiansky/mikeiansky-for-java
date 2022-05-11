package com.winson.advanced.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author winson
 * @date 2022/5/11
 **/
public class ReadWriteLockDemoV3 {

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
    Map<Integer, Integer> cache = new HashMap<>();

    public Integer getCache(Integer key) throws InterruptedException {
        readLock.lock();
        try {
//            System.out.println("after read lock ");
            Integer value = cache.get(key);
            if (value != null) {
                return value;
            }
        } finally {
            readLock.unlock();
        }
//        System.out.println("after read lock ");
        Thread.sleep(50);

        Integer value = null;
        try {
            writeLock.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            value = cache.get(key);
            if (value == null) {
//                return value;
                value = (int) Math.round(Math.random() * Integer.MAX_VALUE);
                cache.put(key, value);
            }

            readLock.lock();
//            return value;
        } finally {
            writeLock.unlock();
        }

        try {
            System.out.println("use lock after get write lock : " + value);
        } finally {
            readLock.unlock();
        }

        return value;
    }


    public static void main(String[] args) throws InterruptedException {

        ReadWriteLockDemoV3 v3 = new ReadWriteLockDemoV3();
        int size = 10;
        CountDownLatch latch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(v3.getCache(20));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    latch.countDown();
                }
            }).start();
        }

        latch.await();

    }

}
