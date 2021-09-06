package com.winson.jdkapi.juc.version_2;

/**
 * @author winson
 * @date 2021/5/10
 **/
public class TestThreadLocal {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("test thread local start ...");

        final ThreadLocal<String> local = new ThreadLocal<>();
        local.set("winson");

        Thread t1 = new Thread(() -> {
            System.out.println("t1 start ... ");
            System.out.println("t1 1 get local : " + local.get());
            local.set("change on thread 1 ");
            System.out.println("t1 2 get local : " + local.get());
            System.out.println("t1 end ... ");
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            System.out.println("t2 start ... ");
            System.out.println("t2 1 get local : " + local.get());
            local.set("change on thread 2 ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2 2 get local : " + local.get());
            System.out.println("t2 end ... ");
        });
        t2.start();

        System.out.println("main 1 get local : " + local.get());
        Thread.sleep(2000);
        System.out.println("main 2 get local : " + local.get());

        t1.join();
        t2.join();

        System.out.println("test thread local end ...");
    }

}
