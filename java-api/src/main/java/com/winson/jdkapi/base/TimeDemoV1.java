package com.winson.jdkapi.base;

/**
 * @author mike ian
 * @date 2023/5/17
 * @desc
 **/
public class TimeDemoV1 {

    public static double microtime() {
        long currentTimeMillis = System.currentTimeMillis();
        long nanoTime = System.nanoTime();
        double microTime = (double) (currentTimeMillis * 1000) + (double) (nanoTime - (nanoTime / 1000000) * 1000000) / 1000;
        return microTime / 1000000;
    }

    public static void main(String[] args) {
        System.out.println(microtime());
    }

}
