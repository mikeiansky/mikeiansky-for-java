package com.winson.juc.version_3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author winson
 * @date 2021/6/18
 **/
public class ReentrantLockDemo {

    public static class DrinkFactory {

        private ReentrantLock lock = new ReentrantLock();

        private Condition notEmpty = lock.newCondition();

        private Condition notFull = lock.newCondition();

        private int drinkBucket = 0;

        private int maxBucket = 10000;

        public void create() {
            lock.lock();
            try {
                while (drinkBucket >= maxBucket) {
                    System.out.println("生产满了，等一等");
                    notFull.await();
                }
                drinkBucket++;
                notEmpty.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void drink() {
            lock.lock();
            try {
                while (drinkBucket <= 0) {
                    System.out.println("没有饮料了，等一等");
                    notEmpty.await();
                }
                drinkBucket--;
                notFull.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("reentrant lock demo start ... ");
        final DrinkFactory drinkFactory = new DrinkFactory();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    drinkFactory.create();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    drinkFactory.drink();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("reentrant lock demo end ... ");
    }

}
