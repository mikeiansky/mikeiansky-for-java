package io.github.mikeiansky.java.concurrent.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author mike ian
 * @date 2025/6/4
 * @desc
 **/
public class LockSupportDemo {

    public static void main(String[] args) {
        System.out.println("app start ... ");
        final Thread thread = new Thread(() -> {
            System.out.println(" before park ");
            LockSupport.park();
            System.out.println(" after park ");
        });
        thread.start();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            LockSupport.unpark(thread);
            LockSupport.unpark(Thread.currentThread());
            LockSupport.unpark(Thread.currentThread());
            System.out.println(" unpark thread ");
        }).start();
        System.out.println("app complete ... ");

    }

}
