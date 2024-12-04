package com.winson.jdkapi.juc.v1.version_2;

import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2021/5/9
 **/
public class TestCountDownLatch {

    public static void main(String[] args) {
        System.out.println(" test count down latch start ... ");
        final int size = 100;
        final CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread run end , index is : " + finalI);
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(" test count down latch end ... ");
    }

}
