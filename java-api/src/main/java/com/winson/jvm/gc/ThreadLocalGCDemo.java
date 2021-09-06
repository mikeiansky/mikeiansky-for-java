package com.winson.jvm.gc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author winson
 * @date 2021/6/28
 **/
public class ThreadLocalGCDemo {

    private static AtomicInteger nextHashCode =
            new AtomicInteger();

    /**
     * The difference between successively generated hash codes - turns
     * implicit sequential thread-local IDs into near-optimally spread
     * multiplicative hash values for power-of-two-sized tables.
     */
    private static final int HASH_INCREMENT = 0x61c88647;

    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(HASH_INCREMENT);
        System.out.println(nextHashCode());
        System.out.println(nextHashCode());
        System.out.println(nextHashCode());
        System.out.println(nextHashCode());
        System.out.println(nextHashCode());
        System.out.println("--------------");

        ThreadLocal<String> localValue = new ThreadLocal<>();
        localValue.set("winson");
        System.out.println("local value before gc : " + localValue.get());
        System.gc();
        Thread.sleep(1000);
        System.out.println("local value after gc : " + localValue.get());

    }

}
