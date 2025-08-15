package io.github.mikeiansky.java.concurrent.future;

import java.util.concurrent.*;

/**
 * @author mike ian
 * @date 2025/6/5
 * @desc
 **/
public class FutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future future = executor.submit(()-> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Task 1 is running in thread: " + Thread.currentThread().getName());
        });
        System.out.println(future.get());
        System.out.println("complete task 1");

        Future future2 = executor.submit(()->{
            Thread.sleep(1000);
           return 12;
        });
        System.out.println(future2.get());
        System.out.println("complete task 2");

        RunnableFuture future3 = (RunnableFuture) executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 101);

        System.out.println(future3.get());
        System.out.println("complete task 3");
        System.out.println("app complete ... ");

    }


}
