package io.github.mikeiansky.java.concurrent.lock;

import java.util.concurrent.Semaphore;

/**
 * @author mike ian
 * @date 2025/6/4
 * @desc
 **/
public class SemaphoreDemo {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    semaphore.acquire();
                    System.out.println("Thread " + Thread.currentThread().getName() + " acquired a permit.");
                    // Simulate some work with the resource
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                    System.out.println("Thread " + Thread.currentThread().getName() + " released a permit.");
                }
            }
        };
        Runnable runnable1 = () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    semaphore.acquire();
                    System.out.println("Thread " + Thread.currentThread().getName() + " acquired a permit.");
                    // Simulate some work with the resource
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                    System.out.println("Thread " + Thread.currentThread().getName() + " released a permit.");
                }
            }
        };

        new Thread(runnable).start();
        new Thread(runnable1).start();

    }

}
