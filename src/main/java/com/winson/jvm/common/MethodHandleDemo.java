package com.winson.jvm.common;

//import com.winson.utils.regular.RegularDemoV1;

import com.winson.utils.common.FakeHandle;
import com.winson.utils.regular.RegularDemoV1;
import com.winson.utils.regular.RegularDemoV2;

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
        System.out.println(MethodHandles.publicLookup());

        try {
            MethodHandles.lookup()
                    .findStatic(RegularDemoV1.class, "regular", MethodType.methodType(void.class))
                    .invokeWithArguments();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        invokeVirtualRegular(new RegularDemoV2());
        invokeVirtualRegular(new FakeHandle());
        invokeSpecialRegular(new FakeHandle());
        invokeSpecialRegular(new MethodHandleDemo());
        invokeToString(new MethodHandleDemo());
    }

    private void special(){
        System.out.println("method handle demo special");
    }

    public static MethodHandles.Lookup lookup(){
        return MethodHandles.lookup();
    }

    public String toString(){
        return "i am method handle demo";
    }

    public static void invokeToString(Object target){
        try {
            MethodHandle methodHandle = MethodHandles.lookup()
                    .findSpecial(String.class, "toString", MethodType.methodType(String.class), String.class);
            System.out.println(methodHandle.bindTo(target).invoke());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public static void invokeSpecialRegular(Object target){
        try {

            MethodHandles.Lookup lookup = (MethodHandles.Lookup) MethodHandles.lookup()
                    .findStatic(target.getClass(), "lookup", MethodType.methodType(MethodHandles.Lookup.class))
                    .invoke();

            MethodHandle methodHandle = lookup
                    .findSpecial(target.getClass(), "special", MethodType.methodType(void.class),target.getClass());

            methodHandle
                    .bindTo(target)
                    .invoke();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public static void invokeVirtualRegular(Object target){
        try {
            MethodHandles.lookup()
                    .findVirtual(target.getClass(), "regular", MethodType.methodType(void.class))
                    .bindTo(target)
                    .invoke();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public static MethodHandle getMethod(Object receiver){
        MethodType methodType = MethodType.methodType(void.class, String.class);
        try {
//            return MethodHandles.lookup().findVirtual(receiver.getClass(), "println", methodType).bindTo(receiver);
            return MethodHandles.publicLookup().findVirtual(receiver.getClass(), "println", methodType).bindTo(receiver);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
