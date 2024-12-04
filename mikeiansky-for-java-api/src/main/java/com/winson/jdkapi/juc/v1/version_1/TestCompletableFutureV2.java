package com.winson.jdkapi.juc.v1.version_1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author winson
 * @date 2021/1/26
 **/
public class TestCompletableFutureV2 {

    public static void main(String[] args) {

        CompletableFuture cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task1 start");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task1 end");
            return "first";
        }).thenApply(target -> {
            System.out.println("Task2 start");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task2 end");
            return target + " - second";
        }).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
            System.out.println("Task3 start");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task3 end");
            return "third";
        }), (s, s2) -> {
            System.out.println("Task4 start");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task4 end");
            return s + " - " + s2;
        });

        try {
            Object result = cf.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
