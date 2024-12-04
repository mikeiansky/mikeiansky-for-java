package com.winson.jdkapi.juc.threadpool.v1;


import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author winson
 * @date 2022/6/9
 **/
public class ExecutorServiceDemoV1 {

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = new ExecutorService() {
            @Override
            public void shutdown() {
                System.out.println("executor service shut down");
            }

            @Override
            public List<Runnable> shutdownNow() {
                System.out.println("executor service shut down now");
                return null;
            }

            @Override
            public boolean isShutdown() {
                return false;
            }

            @Override
            public boolean isTerminated() {
                return false;
            }

            @Override
            public boolean awaitTermination(long timeout,  TimeUnit unit) throws InterruptedException {
                return false;
            }

            @Override
            public <T> Future<T> submit( Callable<T> task) {
                return null;
            }

            @Override
            public <T> Future<T> submit(  Runnable task, T result) {
                return new Future<T>() {
                    @Override
                    public boolean cancel(boolean mayInterruptIfRunning) {
                        return false;
                    }

                    @Override
                    public boolean isCancelled() {
                        return false;
                    }

                    @Override
                    public boolean isDone() {
                        return false;
                    }

                    @Override
                    public T get() throws InterruptedException, ExecutionException {
                        return result;
                    }

                    @Override
                    public T get(long timeout, @NotNull TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                        return null;
                    }
                };
            }

            @Override
            public Future<?> submit(  Runnable task) {
                System.out.println("executor service submit task");
                task.run();
                return null;
            }

            @Override
            public <T> List<Future<T>> invokeAll(  Collection<? extends Callable<T>> tasks) throws InterruptedException {
                System.out.println("executor service invoke all short tasks size is : " + tasks.size());
                return null;
            }

            @Override
            public <T> List<Future<T>> invokeAll(  Collection<? extends Callable<T>> tasks, long timeout,  TimeUnit unit) throws InterruptedException {
                System.out.println("executor service invoke all tasks size is : " + tasks.size());
                return null;
            }

            @Override
            public <T> T invokeAny( Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
                System.out.println("executor service invoke any short tasks size is : " + tasks.size());
                T r = null;
                for (Callable<T> task : tasks) {
                    try {
                        r = task.call();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    if(r != null){
                        break;
                    }
                }
                return r;
            }

            @Override
            public <T> T invokeAny(  Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                System.out.println("executor service invoke any tasks size is : " + tasks.size());
                return null;
            }

            @Override
            public void execute( Runnable command) {
                System.out.println("executor service execute command");
                command.run();
            }
        };

        executorService.shutdown();
        executorService.shutdownNow();
        executorService.submit(new Runnable() {
            @Override
            public void run() {

            }
        });
        Future<String> fs = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        });
        Callable<String> cs1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "callable call string : dada";
            }
        };
        Callable<String> cs2 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "callable call string : dada";
            }
        };
        String result = executorService.invokeAny(Arrays.asList(cs1,cs2));
        System.out.println("invoke result is : " + result);

        Future<String> backFuture = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("---------> back ");
            }
        }, "back");
        System.out.println("backFuture is : "+ backFuture);
        backFuture.cancel(true);
        System.out.println("backFuture.get() is : "+ backFuture.get());

        System.out.println(cs2.call());

    }

}
