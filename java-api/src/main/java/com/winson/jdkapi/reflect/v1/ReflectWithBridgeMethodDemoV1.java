package com.winson.jdkapi.reflect.v1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            System.out.println("getTarget @ Father class ... t : " + t);
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
            System.out.println("getTarget @ Son class ... 11 flag : " + flag);
            super.getTarget(flag);
            System.out.println("getTarget @ Son class ... 22 flag : " + flag);
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

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        Flag flag = new Flag();

        // invoke son method
        Son son = new Son();
        Father father = new Son();
        Father realFather = new Father();
        son.getTarget(flag);
        System.out.println("--");
        father.getTarget(flag);
        System.out.println("--");
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
        System.out.println("--");
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
            System.out.println(method.getName() + " , method is bridge() : " + method.isBridge() + " , param is : " + getParamString(method));
            if (method.isBridge()) {
                System.out.println("------------- bridge -------------");
//                System.out.println();
                System.out.println("==> parameters count : " + method.getParameters().length);
                System.out.println("==> method parameters : " + Arrays.asList(method.getParameters()));
//                method.invoke(son, new Object()); // error
//                method.invoke(realFather, new Object()); // error
                System.out.println("one bridge test end ... ");
            }
        }

    }

    public static String getParamString(Method method) {
        return Stream.of(method.getParameters()).map(Parameter::getType)
                .map(Class::getName)
                .collect(Collectors.toList()).toString();
    }

}
