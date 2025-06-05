package io.github.mikeiansky.java.concurrent.future;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author mike ian
 * @date 2025/6/5
 * @desc
 **/
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture cf1 = CompletableFuture.supplyAsync(()-> {
            System.out.println("run task 1 in thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "001";
        });
        CompletableFuture cf2 = CompletableFuture.supplyAsync(()-> {
            System.out.println("run task 2 in thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "002";
        });
        CompletableFuture cf3 = cf1.thenCombine(cf2, (f1,f2)->{
            System.out.println("combine result: " + f1 + " and " + f2);
            return "003";
        });

        System.out.println(cf3.get());

    }

}
