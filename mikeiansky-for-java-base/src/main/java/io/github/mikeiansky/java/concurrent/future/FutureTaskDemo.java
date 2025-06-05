package io.github.mikeiansky.java.concurrent.future;

import java.util.concurrent.*;

/**
 * @author mike ian
 * @date 2025/5/26
 * @desc
 **/
public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("task thread : " + Thread.currentThread().getName());
                System.out.println("task is running");
                return 0;
            }
        });
//        task.run();
//        Integer ret = task.get();
//        System.out.println("task return : " + ret);

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(task);

    }

}
