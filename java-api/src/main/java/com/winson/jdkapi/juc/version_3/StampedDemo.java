package com.winson.jdkapi.juc.version_3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;
import java.util.function.Supplier;

/**
 * @author winson
 * @date 2021/6/21
 **/
public class StampedDemo {

    private StampedLock lock = new StampedLock();

    private Map<String, String> cache = new HashMap<>();

    public String get(String key, Supplier<String> supplier) {
        long timestamp = lock.readLock();
        String result = cache.get(key);
        lock.unlock(timestamp);
        if (result == null) {
            timestamp = lock.writeLock();
            result = cache.get(key);
            if (result == null) {
                cache.put(key, supplier.get());
                result = cache.get(key);
            }
            lock.unlock(timestamp);
        }
        return result;
    }

    public String getWithOptimistic(String key, Supplier<String> supplier) {
        long timestamp = lock.tryOptimisticRead();
        String result = cache.get(key);
//        lock.unlock(timestamp);
        if (result == null) {
            if(!lock.validate(timestamp)){
                timestamp = lock.readLock();
                System.out.println("validate false -------------> ");
                result = cache.get(key);
                if (result == null) {
                    cache.put(key, supplier.get());
                    result = cache.get(key);
                }
                lock.unlock(timestamp);
            } else{
                timestamp = lock.writeLock();
                result = cache.get(key);
                if (result == null) {
                    System.out.println("write once -------------> ");
                    cache.put(key, supplier.get());
                    result = cache.get(key);
                }
                lock.unlock(timestamp);
            }

        }
        return result;
    }

    public static void main(String[] args) {
        final StampedDemo demo = new StampedDemo();
        int size = 100;
        Thread[] threads = new Thread[size];
        for (int i = 0; i < size; i++) {
            final int threadFlag = i;
            threads[i] = new Thread(() -> System.out.println(demo.getWithOptimistic("key", () -> "haha : " + threadFlag)));
            threads[i].start();
        }

        try {
            for (int i = 0; i < size; i++) {
                threads[i].join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
