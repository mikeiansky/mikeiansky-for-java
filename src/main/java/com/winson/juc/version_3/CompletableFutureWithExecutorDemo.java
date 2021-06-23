package com.winson.juc.version_3;

import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * @author winson
 * @date 2021/6/23
 **/
public class CompletableFutureWithExecutorDemo {

    public static void main(String[] args) {
        System.out.println("main thread id : " + Thread.currentThread().getId());
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, 5, 10,
                TimeUnit.SECONDS, new SynchronousQueue<>());

        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread id : "+Thread.currentThread().getId()+", task1 run at thread : " + Thread.currentThread().getName());
                return "task1 result";
            }
        }, executor);

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("task2 run at thread : " + Thread.currentThread().getName());
                return "task2 result";
            }
        }, executor);

        CompletableFuture<String> task3 = task1.thenCombineAsync(task2, new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) {
                System.out.println("task3 run at thread : " + Thread.currentThread().getName());
                return s + " -> " + s2 + " -> task3 result";
            }
        }, executor);

        CompletableFuture<String> task4 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("task4 run at thread : " + Thread.currentThread().getName());
                return "task4 result";
            }
        }, executor);

        CompletableFuture<String> task5 = task3.thenCombineAsync(task4, new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) {
                System.out.println("task5 run at thread : " + Thread.currentThread().getName());
                return s + " -> " + s2 + " -> task5 result";
            }
        }, executor);

        CompletableFuture<String> task6 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
//                System.out.println("task6 start run at thread : " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("task6 end run at thread : " + Thread.currentThread().getName());
                return "task6 result";
            }
        }, executor);

        try {
            String result = task5.get();
            System.out.println("result : " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
