package com.winson.juc.version_2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author winson
 * @date 2021/5/9
 **/
public class TestCompletableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("test completable future start ... ");

        CompletableFuture cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("cf1 run end ... ");
            return "first result";
        });

        CompletableFuture cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("cf2 run end ... ");
            return "second result";
        });

        CompletableFuture cf3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("cf3 run end ... ");
            return "thrid result";
        });


//        Object result = cf1.thenAccept(o -> {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(" accept ccc : " + o);
//        }).get();
//
//        System.out.println("result is : " + result);

        System.out.println("ready to sleep ... ");
        Thread.sleep(4000);

        System.out.println("test completable future end ... ");
    }

}
