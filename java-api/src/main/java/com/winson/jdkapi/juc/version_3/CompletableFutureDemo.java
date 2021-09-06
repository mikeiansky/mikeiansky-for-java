package com.winson.jdkapi.juc.version_3;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author winson
 * @date 2021/6/22
 **/
public class CompletableFutureDemo {

    public static void main(String[] args) {

        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                System.out.println("task1 run at : " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "水开始烧了";
            }
        });

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                System.out.println("task2 run at : " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "上茶叶";
            }
        });

        CompletableFuture<String> task3 = task1.thenCombine(task2, new BiFunction<String, String, String>() {

            @Override
            public String apply(String s, String s2) {
                System.out.println("task3 run ... ");
                return s + " -> " + s2;
            }

        });

        CompletableFuture<String> task4 = task1.applyToEither(task2, new Function<String, String>() {
            @Override
            public String apply(String s) {
                boolean result = false;
                if(!result){
                    throw new IllegalArgumentException("task4 exception !");
                }
                return s + "apply ";
            }
        }).exceptionally(new Function<Throwable, String>() {
            @Override
            public String apply(Throwable throwable) {
                System.out.println("apply exception : " + throwable.getMessage());
                return "error";
            }
        });

        try {
            String task3Result = task3.get();
            System.out.println("task3 result : " + task3Result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            String task4Result = task4.get();
            System.out.println("task4 result : " + task4Result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

}
