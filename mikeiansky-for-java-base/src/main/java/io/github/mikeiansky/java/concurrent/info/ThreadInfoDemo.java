package io.github.mikeiansky.java.concurrent.info;

import java.util.concurrent.Executors;

/**
 * @author mike ian
 * @date 2025/6/5
 * @desc
 **/
public class ThreadInfoDemo {

    public static void test(){
        Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(1);
        Executors.newSingleThreadExecutor();
        Executors.newSingleThreadScheduledExecutor();
        Executors.newWorkStealingPool();
    }

    public static void main(String[] args) {

        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("test");
        threadLocal.get();


        new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
                System.out.println(threadGroup);
            }
        }).start();
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println(threadGroup);

    }

}
