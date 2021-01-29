package com.winson.juc;

import java.util.concurrent.*;

/**
 * @author winson
 * @date 2021/1/29
 * @desc 测试 completionService 类
 **/
public class TestCompletionService {

    public static void testNormalExecutorService() {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Future<String> f1 = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("f1 thread start .");
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("f1 thread end .");
                return "f1 result";
            }
        });

        Future<String> f2 = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("f2 thread start .");
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("f2 thread end .");
                return "f2 result";
            }
        });

        Future<String> f3 = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("f3 thread start .");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("f3 thread end .");
                return "f3 result";
            }
        });

        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("f1 get result : " + f1.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("f2 get result : " + f2.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("f3 get result : " + f3.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void testCompletionService() {
        ExecutorService service = Executors.newFixedThreadPool(3);
        ExecutorCompletionService<String> ec = new ExecutorCompletionService<>(service);

        ec.submit(() -> {
            System.out.println("f1 start . ");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("f1 end . ");
            return "f1 result";
        });


        ec.submit(() -> {
            System.out.println("f2 start . ");
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("f2 end . ");
            return "f2 result";
        });

        ec.submit(() -> {
            System.out.println("f3 start . ");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("f3 end . ");
            return "f3 result";
        });

        for (int i = 0; i < 3; i++) {
            try {
                Future<String> result = ec.take();
                System.out.println("index - " + i + " , result is : " + result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
//        testNormalExecutorService();

        testCompletionService();
    }

}

