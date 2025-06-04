package io.github.mikeiansky.java.concurrent.lock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author mike ian
 * @date 2025/6/4
 * @desc
 **/
public class LockCacheDemo {

    public static void main(String[] args) {
        Map<String, String> cache = new ConcurrentHashMap<>();
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        final String key = "haha";

        Runnable readTask = () -> {
            String value;
            readLock.lock();
            try {
                value = cache.get(key);
                if (value != null) {
                    System.out.println("Cache hit: " + value);
                    return;
                }
            } finally {
                readLock.unlock();
            }

            writeLock.lock();
            try {
                value = cache.get(key);
                if (value == null) {
                    // 模拟从数据库加载数据
                    System.out.println("Loading data from database...");
                    value = "read value";
                    cache.put(key, value);
                } else {
                    System.out.println("Cache filled by another thread: " + value);
                }
            } finally {
                writeLock.unlock();
            }

            readLock.lock();
            try {
                value = cache.get(key);
                System.out.println("read value is : " + value);
            } finally {
                readLock.unlock();
            }
        };

        Runnable writeTask = () -> {
            writeLock.lock();
            try {
                // 模拟更新数据
                System.out.println("Updating data in cache...");
                cache.put(key, "write value");
            } finally {
                writeLock.unlock();
            }
        };

        new Thread(writeTask).start();
        new Thread(readTask).start();
    }

}
