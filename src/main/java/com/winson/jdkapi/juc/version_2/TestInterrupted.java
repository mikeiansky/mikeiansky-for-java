package com.winson.jdkapi.juc.version_2;

/**
 * @author winson
 * @date 2021/5/7
 **/
public class TestInterrupted {

    public static void main(String[] args) {
        final Thread t1 = new Thread(() -> {
            System.out.println("t1 start ... ");
            System.out.println("t1 isInterrupted ... " + Thread.currentThread().isInterrupted());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 isInterrupted ... " + Thread.currentThread().isInterrupted());
//            boolean flag = true;
//            while (flag){
//
//            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 end .... ");
        });

        t1.start();

        final Thread t2 = new Thread(() -> {
            System.out.println("t2 start ... ");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t1.interrupt();
            System.out.println("t2 t1 is interrupted ... " + t1.isInterrupted());
            System.out.println("t2 end ... ");
        });
        t2.start();
        System.out.println("join t2 ... ");
        try {
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("join t1 ... ");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("t1 on main is interrupted : " + t1.isInterrupted());
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        try {
//            t2.join();
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }

        System.out.println("main end ... ");
    }

}
