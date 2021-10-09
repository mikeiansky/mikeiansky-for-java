package com.winson.jdkapi.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author winson
 * @date 2021/10/9
 **/
public class ReflectWithBridgeMethodDemoV1 {

    public static class Flag<T> {

    }

    public static class SubFlag extends Flag {

    }

    public static class NormalType {

    }

    public static class Father<T> {

        public T getTarget(T t) {
            System.out.println("getTarget @ Father class ... ");
            return t;
        }

        public void normalMethod(Object obj) {

        }

        public <S> void testFlag(Flag<S> flag) {

        }

    }

    public static class Son extends Father<Flag> {

        // has a bridge method
        @Override
        public Flag getTarget(Flag flag) {
            System.out.println("getTarget @ Son class ... ");
            return flag;
        }

        // have no bridge method
        public void normalMethod(Object obj) {

        }

//        @Override
//        public void testFlag(Flag<NormalType> flag) {
//            super.testFlag(flag);
//        }

        // compile error
//        @Override
//        public void testFlag(Flag<NormalType> flag) {
//            super.testFlag(flag);
//        }

    }

    public static void main(String[] args) {

        Flag flag = new Flag();

        // invoke son method
        Son son = new Son();
        son.getTarget(flag);
        // error
//        son.getTarget(new Object());

        // invoke son method
        try {
            Method method = Son.class.getMethod("getTarget", Flag.class);
            method.invoke(son, flag);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // invoke father method, and error
//        try {
//            Method method2 = Son.class.getMethod("getTarget", Object.class);
//            method2.invoke(son, new Object());
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

        System.out.println("getMethods ======= ");
//        Father father = new Father();
        Method[] methods = Son.class.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName() + " , method is bridge() : " + method.isBridge());
            if (method.isBridge()) {
                System.out.println("------------- bridge -------------");
//                System.out.println();
                System.out.println("parameters count : " + method.getParameters().length);
                System.out.println("method parameters : " + Arrays.asList(method.getParameters()));
            }
        }

    }

}
