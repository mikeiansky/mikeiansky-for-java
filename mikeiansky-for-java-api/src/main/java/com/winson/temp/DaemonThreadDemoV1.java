package com.winson.temp;

/**
 * @author winson
 * @date 2022/6/13
 **/
public class DaemonThreadDemoV1 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("daemon thread start ... ");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("daemon thread complete ... ");
            }
        });
        // 主线程结束，这个线程会立即结束
        t1.setDaemon(true);

        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("main app complete ... ");

    }

}
