package com.winson.reflect;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author winson
 * @date 2021/8/6
 **/
public class MethodHandlesDemo {

    public static class Foo {
        public void bar(Object obj){
//            new Exception().printStackTrace();
//            System.out.println("foo::bar(obj) is : " + obj);
        }
    }

//    public static void main(String[] args) throws Throwable {
//
//        MethodHandles.Lookup lookup = MethodHandles.lookup();
//        MethodType methodType = MethodType.methodType(void.class, Object.class);
//        MethodHandle methodHandle = lookup.findStatic(Foo.class, "bar", methodType);
//        methodHandle.invoke(new Object());
//
//    }

    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup l = MethodHandles.lookup();
        MethodType t = MethodType.methodType(void.class, Object.class);
        MethodHandle mh = l.findVirtual(Foo.class, "bar", t);

        long current = System.currentTimeMillis();
        for (int i = 1; i <= 2_000_000_000; i++) {
            if (i % 100_000_000 == 0) {
                long temp = System.currentTimeMillis();
                System.out.println(temp - current);
                current = temp;
            }
//            mh.invokeExact(new Foo(), new Object());
            mh.invoke(new Foo(), new Object());
        }

    }

}
