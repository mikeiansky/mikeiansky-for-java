package com.winson.juc.version_2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author winson
 * @date 2021/5/8
 * @desc 读写锁测试
 **/
public class TestReadWriteLock {

    public static class MyCache {

        private ReadWriteLock lock = new ReentrantReadWriteLock();

        private Lock readLock = lock.readLock();

        private Lock writeLock = lock.writeLock();

        private Map<Integer, Integer> cache = new HashMap<>();

        public Integer get(Integer key) {
            Integer value = null;
            try {
                readLock.lock();

                value = cache.get(key);
            } finally {
                readLock.unlock();
            }
            if(value == null){
                try {
                    writeLock.lock();
                    value = cache.get(key);
                    if(value == null){
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        cache.put(key, 8888);
                        value = 8888;
                    }
                }finally {
                    writeLock.unlock();
                }

            }

            return value;
        }

        public void set(Integer key, Integer value) {
            try {
                writeLock.lock();


            } finally {
                writeLock.unlock();
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("test read write lock start ... ");

        final MyCache myCache = new MyCache();

        Thread[] ts = new Thread[100];
        for (int i = 0; i < ts.length; i++) {
            int finalI = i;
            ts[i] = new Thread(() -> {
                Integer tr = myCache.get(1);
                System.out.println("tr is : " + tr);
            });
            ts[i].start();
        }

        try {
            for (int i = 0; i < ts.length; i++) {
                ts[i].join();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("test read write lock end ...");
    }

}
