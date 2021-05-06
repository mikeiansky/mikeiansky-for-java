package com.winson.juc.version_1;

import java.util.concurrent.*;

/**
 * @author winson
 * @date 2021/1/23
 * @desc 测试future类
 **/
public class TestFuture {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        FutureTask<String> f1 = new FutureTask<>(() -> {
            System.out.println("---------> 烧开水开始");
            Thread.sleep(3000);
            System.out.println("---------> 烧开水完成");
            return "茶壶洗完了";
        });
        new Thread();

        FutureTask<String> f2 = new FutureTask<>(() -> {
            System.out.println("------------> 洗茶叶开始");
            Thread.sleep(1000);
            System.out.println("------------> 洗茶叶完成");

            System.out.println("------------> 洗杯子开始");
            Thread.sleep(1000);
            System.out.println("------------> 洗杯子完成");
            return "杯子和茶叶准备好了";
        });
        Thread t1 = new Thread(f1);
        Thread t2 = new Thread(f2);
        t1.start();
        t2.start();

        try {
//            f1.cancel(true);
//            System.out.println("f1 is done : " + f1.isDone());
//            System.out.println("f1 is isCancelled : " + f1.isCancelled());
            String f1result = f1.get();
            System.out.println(f1result);
            String f2Result = f2.get();
            System.out.println(f2Result);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("main end...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }

}
