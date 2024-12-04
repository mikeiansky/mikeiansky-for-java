package com.winson.jdkapi.juc.v2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author winson
 * @date 2022/5/12
 **/
public class CompletableFutureDemoV1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletionService
//        CompleteFuture cff = null;
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(" one future run ... ");
                return "one step get";
            }
        });
//        cf.complete("test");
        cf.thenAccept(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("then accept ... ");
            }
        });

        CompletableFuture cf3 = cf.thenCombine(CompletableFuture.<String>supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                System.out.println("two future run ... ");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return "this is two ";
            }
        }), new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) {
                System.out.println("one is : " + s);
                System.out.println("two is : " + s2);
                return s + " -> "+s2;
            }
        });

        System.out.println(cf3.get());
        System.out.println("main end ");

        Thread.currentThread().getState();
    }

}
