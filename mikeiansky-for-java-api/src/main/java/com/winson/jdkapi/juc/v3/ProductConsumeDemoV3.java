package com.winson.jdkapi.juc.v3;

import lombok.Data;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mike Ian
 * @date 2023/4/10
 * @desc 生产者消费者模型
 **/
public class ProductConsumeDemoV3 {

    @Data
    public static class Window {
        private int window;

        public Window(int window) {
            this.window = window;
        }

        public void createProduct() {
            window++;
        }

        public void consumeProduct() {
            window--;
        }

    }

    public static void main(String[] args) {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();
        Condition notFullCondition = lock1.newCondition();
        Condition notEmptyCondition = lock1.newCondition();
        int size = 10;
        int maxSize = 3;
        Window window = new Window(0);
        Thread productThread = new Thread(() -> {
            for (int i = 0; i < size; i++) {
                lock1.lock();
                try {
                    while (window.getWindow() >= maxSize) {
                        notFullCondition.await();
                    }
                    window.createProduct();
                    System.out.println("product create id : " + i);
                    notEmptyCondition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock1.unlock();
                }
            }
        });
        productThread.start();

        Thread consumeThread = new Thread(() -> {
            for (int i = 0; i < size; i++) {
                lock1.lock();
                try {
                    while (window.getWindow() < 1) {
                        notEmptyCondition.await();
                    }
                    window.consumeProduct();
                    System.out.println("consume get id : " + i);
                    notFullCondition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock1.unlock();
                }
            }
        });
        consumeThread.start();

    }

}
