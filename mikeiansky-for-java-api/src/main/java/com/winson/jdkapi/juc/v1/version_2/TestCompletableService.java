package com.winson.jdkapi.juc.v1.version_2;

import java.util.concurrent.*;

/**
 * @author winson
 * @date 2021/5/9
 **/
public class TestCompletableService {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("test completable service start ... ");

        ExecutorService service = Executors.newFixedThreadPool(3);

        ExecutorCompletionService ecs = new ExecutorCompletionService(service);

        Future f1 = ecs.submit(() -> {
            Thread.sleep(1000);
            System.out.println("one run ... ");
            return "one result";
        });

        Future f2 = ecs.submit(() -> {
            Thread.sleep(2000);
            System.out.println("two run ... ");
            return "two result";
        });

        Future f3 = ecs.submit(() -> {
            Thread.sleep(3000);
            System.out.println("three run ... ");
            return "three result";
        });

//        System.out.println(f1.get());

        for (int i = 0; i < 3; i++) {
            Future fr = ecs.take();
            System.out.println("take-" + i);
            if(fr != null){
                System.out.println("take result is : "+ fr.get());
            }
        }

        service.shutdown();
        System.out.println("test completable service end ...");
    }

}
