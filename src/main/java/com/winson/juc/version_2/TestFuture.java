package com.winson.juc.version_2;

import java.util.concurrent.*;

/**
 * @author winson
 * @date 2021/5/9
 **/
public class TestFuture {

    public static void main(String[] args) {
        System.out.println("test future start ... ");

        ExecutorService service = Executors.newFixedThreadPool(3);
        Future f1 = service.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        try {
            System.out.println("f1 : " + f1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Future<String> f2 = service.submit(() -> {
            Thread.sleep(1000);
            return "hello!";
        });
        try {
            System.out.println("f2 : " + f2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        String temp = "this is temp";
        Future<String> f3 = service.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, temp);
        try {
            System.out.println("f3 : " + f3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        service.shutdown();
        System.out.println("test future end ... ");
    }

}
