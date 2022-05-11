package com.winson.advanced.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author winson
 * @date 2022/5/11
 **/
public class ReadWriteLockDemoV2 {

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
    Map<Integer, Integer> cache = new HashMap<>();

    public Integer getCache(Integer key) {
        readLock.lock();
        try {
            Integer value = cache.get(key);
            if (value != null) {
                return value;
            }

//            Integer value = null;
            try {
                // TODO 锁升级会造成死锁
                writeLock.lock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                value = cache.get(key);
                if (value != null) {
                    return value;
                }
                value = (int) Math.round(Math.random() * Integer.MAX_VALUE);
                cache.put(key, value);
                return value;
            } finally {
                writeLock.unlock();
            }

        } finally {
            readLock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {

        ReadWriteLockDemoV2 v1 = new ReadWriteLockDemoV2();
        int size = 10;
        CountDownLatch latch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(v1.getCache(20));
                    latch.countDown();
                }
            }).start();
        }

        latch.await();

    }

}
