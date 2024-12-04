package com.winson.jdkapi.reflect.v1;

import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author winson
 * @date 2021/10/5
 **/
public class GenericWithReflectDemoV2 {

    public static class TypeOne {
        public void doOne() {
            System.out.println("do one thing ... ");
        }
    }

    public static class TypeTwo {
        public void doTwo() {
            System.out.println("do two thing ... ");
        }
    }

//    public static class AbstractGenericObj<T> {
//
//        public T invokeType(T t){
//            return t;
//        }
//
//    }

    public static class GenericObj<T> {

        private T t;

        public GenericObj(T t) {
            this.t = t;
        }

        public T getTarget() {
            return t;
        }
    }

    public static class GenericOut {

        public GenericObj<TypeOne> invokeByGenericObj(GenericObj<TypeOne> genericObj) {
            System.out.println("invoke by generic obj ... generic obj : " + genericObj.getTarget());
            // 这里可能会报错
            genericObj.getTarget().doOne();
            return new GenericObj<>(new TypeOne());
        }


    }

    public static void main(String[] args) {

        Class clazz = GenericOut.class;
        GenericOut genericOut = new GenericOut();
        genericOut.invokeByGenericObj(new GenericObj<TypeOne>(new TypeOne()));

        try {
            Method method = clazz.getMethod("invokeByGenericObj", GenericObj.class);
            method.invoke(genericOut, new GenericObj<TypeTwo>(new TypeTwo()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // get Declared Methods

        System.out.println("======== get declared methods ");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

    }

}
