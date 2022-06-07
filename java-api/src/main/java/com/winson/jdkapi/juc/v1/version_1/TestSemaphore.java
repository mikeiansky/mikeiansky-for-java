package com.winson.jdkapi.juc.v1.version_1;

import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * @author winson
 * @date 2021/1/20
 **/
public class TestSemaphore {


    public static class ObjPool<T, R> {

        private final Vector<T> pool;
        private final Semaphore semaphore;

        public ObjPool(int size, T t) {
            pool = new Vector<>();
            for (int i = 0; i < size; i++) {
                pool.add(t);
            }
            semaphore = new Semaphore(pool.size());
            System.out.println("pool size : " + pool.size());
        }

        public void exec(Function<T, R> runnable) {
            try {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                T t = pool.remove(0);
                runnable.apply(t);
                pool.add(t);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }

    }


    public static void main(String[] args) {
        ObjPool<Long, String> objPool = new ObjPool<>(5, 3L);
        int size = 10;
        Thread[] threads = new Thread[size];
        for (int i = 0; i < size; i++) {
            final int id = i;
            threads[i] = new Thread(() -> objPool.exec(aLong -> {
                System.out.println("run at id : " + id + " , data is : " + aLong);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "aa";
            }));
            threads[i].start();
        }
        for (Thread thread : threads) {
            System.out.println("add join--->");
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("main thread end . ");

    }

}
