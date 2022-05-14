package com.winson.advanced.concurrency.collection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

/**
 * @author winson
 * @date 2022/5/14
 **/
public class SynchronousQueueDemoV1 {

    public static void main(String[] args) {

//        SynchronousQueue<Integer> queue = new SynchronousQueue<>(true);
//        SynchronousQueue<Integer> queue = new SynchronousQueue<>(true);
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                queue.add(1);
                System.out.println("add success");
            }
        }).start();
//        queue.add(1);
        queue.offer(1);
        System.out.println("offer 1");
        try {
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end...");
        Thread.State ts = null;
//        ExecutorService es = Executors.newCachedThreadPool();
//        es.submit(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println("action run");
//            }
//        });
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        es.shutdown();
//        System.out.println("main end ");
    }

}
