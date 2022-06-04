package com.winson.jdkapi.base;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author winson
 * @date 2021/10/16
 **/
public class AtomicDemoV1 {

    public static void main(String[] args) {
        AtomicInteger idx = new AtomicInteger();
        int length = 1;
        int result = idx.getAndIncrement() & length - 1;
        System.out.println(result);
    }

}
