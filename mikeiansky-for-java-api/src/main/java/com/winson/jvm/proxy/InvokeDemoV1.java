package com.winson.jvm.proxy;

import java.lang.invoke.*;
import java.lang.reflect.Constructor;

/**
 * @author winson
 * @date 2021/9/1
 * @desc lambda 表达式原理测试
 **/
public class InvokeDemoV1 {

    public interface MyFunction {
        void run();
    }


    public static void realRun() {
        System.out.println("static real run on demo v2");
    }

    public void test(MyFunction function) {
        function.run();
        System.out.println("test :: " + this);
    }

    public void say(String msg){
        System.out.println("hello : " + msg);
    }


    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup caller = MethodHandles.lookup();
        // run
        String invokedName = "run";
        // Lcom.winson.jvm.invoke.InvokeDemoV1$MyFunction
        MethodType invokedType = MethodType.methodType(MyFunction.class);
        // ()V
        MethodType samMethodType = MethodType.methodType(void.class);
        // invokestatic InvokeDemoV1.realRun:()V
        MethodHandle implMethod = caller.findStatic(InvokeDemoV1.class, "realRun", samMethodType);
        MethodType instantiatedMethodType = samMethodType; // ()V
        // invokedynamic
        CallSite callSite = LambdaMetafactory.metafactory(caller, invokedName, invokedType,
                samMethodType, implMethod, instantiatedMethodType);

        Object obj = callSite.dynamicInvoker().invokeWithArguments();
        System.out.println(obj);
        System.out.println(obj instanceof MyFunction);
//        System.out.println(obj instanceof MethodHandles);
//        System.out.println(obj instanceof InvokeDemoV1);


        Class clazz = MethodHandles.Lookup.class;
        Constructor constructor = clazz.getDeclaredConstructor(Class.class);
        constructor.setAccessible(true);
        MethodHandles.Lookup functionLoopUp = (MethodHandles.Lookup) constructor.newInstance(obj.getClass());
//        System.out.println(functionLoopUp);
        functionLoopUp.findVirtual(obj.getClass(), "run", MethodType.methodType(void.class))
                .bindTo(obj)
                .invoke();

        caller.findVirtual(InvokeDemoV1.class, "say", MethodType.methodType(void.class, String.class))
                .bindTo(new InvokeDemoV1())
                .invoke("hello");

        System.out.println("app end");
    }

}
