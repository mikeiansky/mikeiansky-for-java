package com.winson.base.math;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author winson
 * @date 2022/1/10
 **/
public class MathDemo {

    public static void main(String[] args) {


        long s = System.currentTimeMillis() / 1000;
        double div = 10000000000.0;
        System.out.println(s/div);

        AtomicInteger ai = new AtomicInteger();
        ai.set(Integer.MAX_VALUE);
        System.out.println(ai.get());
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.getAndIncrement());

    }

}
