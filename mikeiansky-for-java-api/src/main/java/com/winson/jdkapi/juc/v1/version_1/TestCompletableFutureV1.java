package com.winson.jdkapi.juc.v1.version_1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author winson
 * @date 2021/8/30
 **/
public class TestCompletableFutureV1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final CompletableFuture<String> cf = new CompletableFuture<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cf.complete("hello world");
            }
        }).start();

        String result = cf.get();
        System.out.println("completable future get result : " + result);
    }

}
