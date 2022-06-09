package com.winson.jdkapi.calc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author winson
 * @date 2022/6/9
 **/
public class BitCalcDemoV1 {


    public static void main(String[] args) {

        int a = 1 << 2;
        System.out.println(a);
        System.out.println(Integer.toBinaryString(a));
        int b = ~a;
        System.out.println(Integer.toBinaryString(b));
        int c = 1 << 3;
        System.out.println(Integer.toBinaryString(c));
        int d = b & c;
        System.out.println(Integer.toBinaryString(d));
        int e = b & ~c;
        System.out.println(Integer.toBinaryString(e));
        System.out.println("----");
        int uone = -1;
        System.out.println(Integer.toBinaryString(uone));
        int uone3 = uone << 3;
        System.out.println(Integer.toBinaryString(uone3));
        int countBits = Integer.SIZE - 3;
        int capacity = (1 << countBits) - 1;
        int running = -1 << countBits;
        int shutDown = 0 << countBits;
        int stop = 1 << countBits;
        int tidying = 2 << countBits;
        System.out.println("capacity : " + capacity);
        System.out.println("running : " + running);
        System.out.println(Integer.toBinaryString(running));
        System.out.println("shutDown : " + shutDown);
        System.out.println("000" + Integer.toBinaryString(shutDown));
        System.out.println("stop : " + stop);
        System.out.println("000" + Integer.toBinaryString(stop));
        System.out.println("tidying : " + tidying);
        System.out.println("00" + Integer.toBinaryString(tidying));
        System.out.println("capacity : ");
        System.out.println("000"+Integer.toBinaryString(capacity));
        System.out.println(Integer.toBinaryString(running));
//        System.out.println(Integer.toBinaryString(-1));
        System.out.println("-- 1 --");
        int rr = running | 0;
        AtomicInteger ai = new AtomicInteger(rr);
        System.out.println("000" + Integer.toBinaryString(capacity));
        System.out.println(Integer.toBinaryString(~capacity));
        System.out.println(Integer.toBinaryString(ai.getAndIncrement()));
        System.out.println(Integer.toBinaryString(ai.getAndIncrement()));
        // 00011111111111111111111111111111
        // 11100000000000000000000000000000
//        int count1 = ai.getAndIncrement() & capacity;
//        int count2 = ai.getAndIncrement() & capacity;
//        int count3 = ai.getAndIncrement() & capacity;
//        int count4 = ai.getAndIncrement() & capacity;
//        System.out.println(count1);
//        System.out.println(count2);
//        System.out.println(count3);
//        System.out.println(count4);
//        System.out.println(ai.get());

    }

}
