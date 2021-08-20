package com.winson.jdkapi.juc.version_1;

/**
 * @author winson
 * @date 2021/4/30
 **/
public class ThreadAdd10K {

    public static volatile int count = 0;

    public static void add10K(){
        for (int i = 0; i < 1000000; i++) {
            count += 1;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> add10K());
        Thread t2 = new Thread(() -> add10K());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);
    }

}
