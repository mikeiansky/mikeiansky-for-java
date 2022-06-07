package com.winson.jdkapi.juc.v2;

import java.util.concurrent.Semaphore;

/**
 * @author winson
 * @date 2022/5/14
 **/
public class SemaphoreDemoV2 {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(0);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("action run @ : " + finalI);
                }
            }).start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("semaphore release 5");
        semaphore.release(5);

        while (semaphore.availablePermits() != 0){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        semaphore.release(5);

    }

}
