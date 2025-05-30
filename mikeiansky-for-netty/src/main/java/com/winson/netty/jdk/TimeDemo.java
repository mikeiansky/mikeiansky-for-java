package com.winson.netty.jdk;

/**
 * @author mike ian
 * @date 2025/5/30
 * @desc
 **/
public class TimeDemo {

    public static void main(String[] args) throws InterruptedException {

        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());
//        Thread.sleep(1000);
        System.out.println(System.currentTimeMillis());
        System.out.println(System.nanoTime());

        int value = (int) Long.MAX_VALUE;
        System.out.println(value);
        value = Integer.MAX_VALUE;
        System.out.println(value);
        System.out.println(++value);

    }

}
