package com.winson.jdkapi.juc.v3;

import lombok.Data;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mike Ian
 * @date 2023/4/10
 * @desc 多生产者多消费者模型
 **/
public class MultiProducerConsumerDemoV3 {

    @Data
    public static class Factory{

        private int product;

        public void createProduct(){
            product++;
        }

        public void consumeProduct(){
            product--;
        }

    }

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("thread 1 start");
                condition.await();
                System.out.println("thread 1 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

    }



}
