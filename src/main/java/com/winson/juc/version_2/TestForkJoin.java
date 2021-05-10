package com.winson.juc.version_2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author winson
 * @date 2021/5/10
 **/
public class TestForkJoin {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("test fork join start ... ");

        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        RecursiveTask<Integer> task = new RecursiveTask<Integer>() {
            @Override
            protected Integer compute() {
                System.out.println("compute ... ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                RecursiveTask<Integer> rt1 = new RecursiveTask<Integer>() {
                    @Override
                    protected Integer compute() {
                        System.out.println("compute 3 start ... " + Thread.currentThread().getName());
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("compute 3 end ... " + Thread.currentThread().getName());
                        return 3;
                    }
                };

                RecursiveTask<Integer> rt2 = new RecursiveTask<Integer>() {
                    @Override
                    protected Integer compute() {
                        System.out.println("compute 4 start ... " + Thread.currentThread().getName());
                        try {
                            Thread.sleep(4000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("compute 4 end ... " + Thread.currentThread().getName());
                        return 4;
                    }
                };
                rt1.fork();
                rt2.fork();
                System.out.println("ready compute and join ... " + Thread.currentThread().getName());
                return rt1.join() + rt2.join();
            }
        };

        ForkJoinTask<Integer> ft = forkJoinPool.submit(task);
        System.out.println("before join ... ");
//        ft.join();
        ft.fork();
        ft.fork();
        ft.fork();
        ft.join();
//        ft.join();
        System.out.println("after join ... ");
//        System.out.println("result : "+ft.get());
//        Thread.sleep(5000);
        System.out.println("run end ... ");
        System.out.println("test fork join end ... ");
    }

}
