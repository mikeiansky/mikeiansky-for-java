package com.winson.jdkapi.juc.threadpool.v1;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author winson
 * @date 2022/6/9
 **/
public class AbstractExecutorServiceDemoV1 {

    public static void main(String[] args) throws Exception {

        AbstractExecutorService aes = new AbstractExecutorService() {
            @Override
            public void execute(@NotNull Runnable command) {
                System.out.println(Thread.currentThread().getName() + " , aes command : " + command);
                command.run();
            }

            @Override
            public void shutdown() {
                System.out.println("aes shutdown");

            }

            @NotNull
            @Override
            public List<Runnable> shutdownNow() {
                System.out.println("aes shutdownNow");

                return new ArrayList<>();
            }

            @Override
            public boolean isShutdown() {
                System.out.println("aes isShutdown");
                return false;
            }

            @Override
            public boolean isTerminated() {
                System.out.println("aes isTerminated");
                return false;
            }

            @Override
            public boolean awaitTermination(long timeout, @NotNull TimeUnit unit) throws InterruptedException {
                System.out.println("aes awaitTermination");
                return false;
            }
        };

        aes.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " , hahaha");
            }
        });

        FutureTask ft1 = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("ft1 execute");
                return "ft1-flag";
            }
        });

//        Callable ca = Executors.callable(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("es cl r");
//            }
//        }, "cw");
//        System.out.println(ca.call());


        ft1.run();
        ft1.run();
        ft1.run();
        System.out.println(ft1.cancel(true));

        System.out.println(ft1.get());

    }

}
