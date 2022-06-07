package com.winson.jdkapi.juc.v1.version_3;

import java.util.concurrent.*;

/**
 * @author winson
 * @date 2021/6/23
 **/
public class CompletionServiceDemo {

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10,
                10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        CompletionService<String> service = new ExecutorCompletionService<>(executor);

        service.submit(() -> {
            System.out.println("task1 start ...");
            Thread.sleep(1000);
            System.out.println("task1 end ...");
            return "task1";
        });

        service.submit(() -> {
            System.out.println("task2 start ...");
            Thread.sleep(2000);
            System.out.println("task2 end ...");
            return "task2";
        });

        service.submit(() -> {
            System.out.println("task3 start ...");
            Thread.sleep(3000);
            System.out.println("task3 end ...");
            return "task3";
        });

        for (int i = 0; i < 3; i++) {
            try {
                System.out.println("id-[" + (i + 1) + "]:" + service.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("main end ... ");

    }

}
