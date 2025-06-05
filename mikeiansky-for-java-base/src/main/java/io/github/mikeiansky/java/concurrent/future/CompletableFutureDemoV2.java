package io.github.mikeiansky.java.concurrent.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;

/**
 * @author mike ian
 * @date 2025/6/5
 * @desc
 **/
public class CompletableFutureDemoV2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("run - 001");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "001";
        });
        CompletableFuture cf4 = cf1.thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println("run - 002");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "002";
        }), (o1, o2)->{
            return new Object[] {o1,o2};
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println("run - 004");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "004";
        }), (o1, o2)->{
            System.out.println(o1 + " xxxx " + o2);
            return o1 + "-" + o2;
        });

        System.out.println(cf4.get());

        System.out.println("app complete ... ");
    }

}
