package com.winson.advanced.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.StampedLock;

/**
 * @author winson
 * @date 2022/5/11
 **/
public class StampedLockDemoV2 {

    StampedLock lock = new StampedLock();

    Map<Integer, Integer> cache = new HashMap<>();

    public Integer getCache(Integer key) {
        Integer value = null;
        long rot = lock.tryOptimisticRead();
        value = cache.get(key);
        if (value != null && lock.validate(rot)) {
            return value;
        }

        long rt = lock.readLock();
        System.out.println("read rt : " + rt);
        try {
            value = cache.get(key);
            if (value != null) {
                return value;
            }
        } finally {
            lock.unlockRead(rt);
        }

        long wt = lock.writeLock();
        System.out.println("write wt : " + wt);
        try {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            value = cache.get(key);
            if (value != null) {
                return value;
            }
            value = Math.toIntExact(Math.round(Math.random() * Integer.MAX_VALUE));
            cache.put(key, value);
            return value;
        } finally {
            lock.unlockWrite(wt);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        StampedLockDemoV2 sld = new StampedLockDemoV2();
        int size = 10;
        CountDownLatch latch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(sld.getCache(20));
                    latch.countDown();
                }
            }).start();
        }

        latch.await();
    }

}
