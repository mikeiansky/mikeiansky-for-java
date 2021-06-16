package com.winson.juc.version_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author winson
 * @date 2021/6/16
 **/
public class TestSemaphoreObjPoolV3 {

    static class ObjectPool<T> {

        private final Semaphore semaphore;

        private final Vector<T> objectPool = new Vector<>();

        public ObjectPool(int size, Function<Integer, T> supply) {
            this.semaphore = new Semaphore(size);
            for (int i = 0; i < size; i++) {
                objectPool.add(supply.apply(i));
            }
        }

        public void exec(Consumer<T> consumer) {
            T t = null;
            try {
                semaphore.acquire();
                t = objectPool.remove(0);
                consumer.accept(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (t != null) {
                    objectPool.add(t);
                }
                semaphore.release();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("test semaphore object pool version 3.0 start ... ");

        final ObjectPool<String> objectPool = new ObjectPool<>(3, integer -> "object-" + integer);

        int size = 20;
        Thread[] threads = new Thread[size];
        for (int i = 0; i < size; i++) {
            final int threadFlag = i;
            threads[i] = new Thread(() -> objectPool.exec(s -> {
                System.out.println("thread-" + threadFlag + " start consume object is : " + s + " <<<<< ");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread-" + threadFlag + " complete consume object is : " + s + " >>>>>> ");
            }));
            threads[i].start();
        }

        for (int i = 0; i < size; i++) {
            threads[i].join();
        }

        System.out.println("test semaphore object pool version 3.0 end ... ");
    }

}
