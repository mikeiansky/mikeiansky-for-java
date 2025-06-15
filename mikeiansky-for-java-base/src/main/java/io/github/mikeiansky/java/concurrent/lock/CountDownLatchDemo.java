package io.github.mikeiansky.java.concurrent.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @author mike ian
 * @date 2025/6/4
 * @desc
 **/
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            final int index = i;
            new Thread(() -> {
                try {
                    // 模拟任务执行
                    System.out.println("Task " + index + " is running");
                    Thread.sleep(1000); // 模拟任务耗时
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    System.out.println(countDownLatch.getCount());
                    countDownLatch.countDown(); // 任务完成，计数器减一
                    System.out.println(countDownLatch.getCount());
                    countDownLatch.countDown(); // 任务完成，计数器减一
                    System.out.println(countDownLatch.getCount());
                    countDownLatch.countDown(); // 任务完成，计数器减一
                    System.out.println(countDownLatch.getCount());
                }
            }).start();
        }

        countDownLatch.await();
        System.out.println("Waiting for tasks to complete...");

    }

}
