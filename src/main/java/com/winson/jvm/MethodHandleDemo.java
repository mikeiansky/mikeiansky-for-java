package com.winson.jvm;

import java.io.PrintStream;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author winson
 * @date 2021/7/18
 **/
public class MethodHandleDemo {

    public static class MyPrint {
        public void println(String msg){
            System.out.println("my print print msg : " + msg);
        }
    }

    public static void main(String[] args) {

        System.out.println("hello");

        Object obj1 = new MyPrint();
        Object obj2 = System.out;
        try {
            getMethod(obj1).invoke("winson1");
            getMethod(obj2).invoke("winson2");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }

    public static MethodHandle getMethod(Object receiver){
        MethodType methodType = MethodType.methodType(void.class, String.class);
        try {
            return MethodHandles.lookup().findVirtual(receiver.getClass(), "println", methodType).bindTo(receiver);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
