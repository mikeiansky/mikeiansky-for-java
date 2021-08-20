package com.winson.jdkapi.juc.version_1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * @author winson
 * @date 2021/1/26
 **/
public class TestCompletableFuture {

    public static void main(String[] args) {
        System.out.println("Test completable future ... ");

        CompletableFuture<Void> cf1 = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println("Task1 start.");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task1 end.");
            }
        });

        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                System.out.println("Task2 start.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task2 end.");
                return "hello";
            }
        });

        CompletableFuture<String> cf3 = cf1.thenCombine(cf2, new BiFunction<Void, String, String>() {
            @Override
            public String apply(Void unused, String s) {
                System.out.println("Task3 get param is : " + s);
                return "apply - " + s;
            }
        });

        try {
            String result = cf3.get();
            System.out.println("all result is : " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("main end.");

    }

}
