package io.github.mikeiansky.java.concurrent.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mike ian
 * @date 2025/6/4
 * @desc
 **/
public class LockConditionDemo {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition notEmpty = lock.newCondition();
        Condition notFull = lock.newCondition();
        int capacity = 1;
        AtomicInteger count = new AtomicInteger(0);

        Runnable producer = () -> {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    while (count.get() == capacity) {
                        notFull.await();
                    }
                    count.incrementAndGet();
                    System.out.println("生产者生产，第 " + i + " 次，当前数量: " + count.get());
                    notEmpty.signalAll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        };

        Runnable consumer = () -> {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    while (count.get() == 0) {
                        notEmpty.await();
                    }
                    count.decrementAndGet();
                    System.out.println("消费者消费，第 " + i + " 次，当前数量: " + count.get());
                    notFull.signalAll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }

}
