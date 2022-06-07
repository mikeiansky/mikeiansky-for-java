package com.winson.jdkapi.juc.v1.version_3;

import java.util.concurrent.*;

/**
 * @author winson
 * @date 2021/6/22
 **/
public class FutureTaskDemo {

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(5);

        FutureTask<String> task1 = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "洗水壶-》烧水-》上茶叶-》";
            }
        });

        FutureTask<String> task2 = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "洗茶叶-》龙井茶";
            }
        });

        service.submit(task1);
        service.submit(task2);

        String task1Result = null;
        try {
            task1Result = task1.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        String task2Result = null;
        try {
            task2Result = task2.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(task1Result + task2Result);


    }

}
