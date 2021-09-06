package com.winson.jdkapi.juc.version_1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author winson
 * @date 2021/1/20
 **/
public class TestCondition {

    public static void main(String[] args){
        System.out.println("Test condition");
        final ReentrantLock lock = new ReentrantLock();
        Condition customer = lock.newCondition();
        Thread thread1 = new Thread(() -> {
            lock.lock();
            System.out.println("thread1 start .");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                customer.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
            System.out.println("thread1 end .");
        });
        Thread thread2 = new Thread(() -> {

            lock.lock();
            System.out.println("thread2 start .");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            customer.signalAll();
            lock.unlock();
            System.out.println("thread2 end .");
        });

        try {
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main thread end.");
    }

}
