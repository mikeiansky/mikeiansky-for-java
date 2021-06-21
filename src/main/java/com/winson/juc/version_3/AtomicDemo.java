package com.winson.juc.version_3;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author winson
 * @date 2021/6/21
 **/
public class AtomicDemo {

    public static void main(String[] args) {

        AtomicIntegerArray array = new AtomicIntegerArray(3);
        array.set(2,3);
        System.out.println(array.get(2));

        AtomicReference<AtomicDemo> atomicReference = new AtomicReference<>();
        System.out.println(atomicReference.get());
        atomicReference.set(new AtomicDemo());
        System.out.println(atomicReference.get());
        atomicReference.set(new AtomicDemo());
        System.out.println(atomicReference.get());
        System.out.println(atomicReference.get());

    }

}
