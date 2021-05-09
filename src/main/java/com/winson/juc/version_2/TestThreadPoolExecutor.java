package com.winson.juc.version_2;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author winson
 * @date 2021/5/9
 **/
public class TestThreadPoolExecutor {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("test thread pool executor start ... ");
        final AtomicInteger count = new AtomicInteger();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,
                4,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1),
                r -> {
                    System.out.println("create thread ... " + count.incrementAndGet());
                    return new Thread(r, "winson-thread");
                },
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("my reject exception ... ");
                    }
                });

        executor.execute(() -> {
            System.out.println("1 thread start run : " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1 thread stop run : " + Thread.currentThread().getName());
        });

        executor.execute(() -> {
            System.out.println("2 thread start run : " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2 thread stop run : " + Thread.currentThread().getName());
        });

//        Thread.sleep(50);

        executor.execute(() -> {
            System.out.println("3 thread start run : " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("3 thread stop run : " + Thread.currentThread().getName());
        });

//        Thread.sleep(2000);

        executor.execute(() -> {
            System.out.println("4 thread start run : " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("4 thread stop run : " + Thread.currentThread().getName());
        });
//        Thread.sleep(2000);

        executor.execute(() -> {
            System.out.println("5 thread start run : " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("5 thread stop run : " + Thread.currentThread().getName());
        });

//        Thread.sleep(2000);
        executor.execute(() -> {
            System.out.println("6 thread start run : " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("6 thread stop run : " + Thread.currentThread().getName());
        });

        try {
            Thread.sleep(1000 * 60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("test thread pool executor end ... ");
    }

}
