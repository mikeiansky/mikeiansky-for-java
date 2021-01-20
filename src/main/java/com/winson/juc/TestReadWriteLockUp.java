package com.winson.juc;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author winson
 * @date 2021/1/20
 **/
public class TestReadWriteLockUp {

    public static class MyCache<K, V> {
        private HashMap<K, V> cache = new HashMap<>();
        private ReadWriteLock lock = new ReentrantReadWriteLock();
        private Lock readLock = lock.readLock();
        private Lock writeLock = lock.writeLock();

        public V get(K k, V temp) {
            V result = null;
            System.out.println(Thread.currentThread().getName() + " 准备获取数据-1");
            readLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "准备获取数据-2");
                result = cache.get(k);
                System.out.println(Thread.currentThread().getName() + " 准备获取数据结果是：" + result);
                if (result == null) {
                    try {
                        writeLock.lock();
                        result = cache.get(k);
                        if (result == null) {
                            System.out.println(Thread.currentThread().getName() + "获取缓存数据-3");
                            Thread.sleep(2000);
                            cache.put(k, temp);
                            result = temp;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        writeLock.unlock();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
            return result;
        }
    }

    public static void main(String[] args) {
        final MyCache<String, String> cache = new MyCache<>();
        int size = 20;
        Thread[] threads = new Thread[size];
        for (int i = 0; i < size; i++) {
            threads[i] = new Thread(() -> {
                String result = cache.get("name", "winson");
                System.out.println(result);
            });
            threads[i].start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main run end.");
    }

}
