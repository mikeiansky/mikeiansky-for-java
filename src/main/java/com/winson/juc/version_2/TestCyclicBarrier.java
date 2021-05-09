package com.winson.juc.version_2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author winson
 * @date 2021/5/9
 **/
public class TestCyclicBarrier {

    public static void main(String[] args) {
        System.out.println(" test cyclic barrier start ... ");

        final CyclicBarrier cb = new CyclicBarrier(10, () -> System.out.println("batch once"));
        int size = 100;
        for (int i = 0; i < size; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("thread index " + finalI + " end ... ");
            }).start();
        }
        System.out.println(" test cyclic barrier end ...");
    }

}
