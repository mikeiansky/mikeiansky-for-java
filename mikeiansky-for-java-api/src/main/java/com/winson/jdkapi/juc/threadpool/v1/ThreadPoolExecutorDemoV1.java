package com.winson.jdkapi.juc.threadpool.v1;

import java.util.concurrent.*;

/**
 * @author winson
 * @date 2022/6/9
 **/
public class ThreadPoolExecutorDemoV1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadPoolExecutor tpe = new ThreadPoolExecutor(
                1,1,10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1024)
        );
//        tpe.allowCoreThreadTimeOut(true);

        Future ft = tpe.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " ------ sub thread run ... ");
            }
        });

        ft.get();
        System.out.println("main end ... ");
        tpe.shutdown();

    }

}
