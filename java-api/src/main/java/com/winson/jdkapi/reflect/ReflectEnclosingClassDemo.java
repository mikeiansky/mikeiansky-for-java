package com.winson.jdkapi.reflect;

import org.apache.regexp.RE;

import java.util.Arrays;

/**
 * @author winson
 * @date 2021/10/9
 **/
public class ReflectEnclosingClassDemo {

    public class OutFlag {
        public void testEnclosing(){
            System.out.println("============= anonymous class on OutFlag#testEnclosing =============");
//            anonymousMethod();

            Runnable runnable = new Runnable() {

                @Override
                public void run() {

                }

            };
            Class<?> clazz = runnable.getClass();
            System.out.println("class : " + clazz);
            System.out.println("class.getSuper() : " + clazz.getSuperclass());
            System.out.println("class.getInterfaces() : " + Arrays.asList(clazz.getInterfaces()));
            System.out.println("clazz.getEnclosingClass() : " + clazz.getEnclosingClass());
            System.out.println("clazz.getEnclosingConstructor() : " + clazz.getEnclosingConstructor());
            System.out.println("clazz.getEnclosingMethod() : " + clazz.getEnclosingMethod());
        }
    }

    public ReflectEnclosingClassDemo(){
        System.out.println("============= anonymous class on constructor =============");
//        anonymousMethod();

        Runnable runnable = new Runnable() {

            @Override
            public void run() {

            }

        };
        Class<?> clazz = runnable.getClass();
        System.out.println("class : " + clazz);
        System.out.println("class.getSuper() : " + clazz.getSuperclass());
        System.out.println("class.getInterfaces() : " + Arrays.asList(clazz.getInterfaces()));
        System.out.println("clazz.getEnclosingClass() : " + clazz.getEnclosingClass());
        System.out.println("clazz.getEnclosingConstructor() : " + clazz.getEnclosingConstructor());
        System.out.println("clazz.getEnclosingMethod() : " + clazz.getEnclosingMethod());

        new OutFlag().testEnclosing();
    }

    public static void anonymousMethod(){
        Runnable runnable = new Runnable() {

            @Override
            public void run() {

            }

        };
        Class<?> clazz = runnable.getClass();
        System.out.println("class : " + clazz);
        System.out.println("class.getSuper() : " + clazz.getSuperclass());
        System.out.println("class.getInterfaces() : " + Arrays.asList(clazz.getInterfaces()));
        System.out.println("clazz.getEnclosingClass() : " + clazz.getEnclosingClass());
        System.out.println("clazz.getEnclosingConstructor() : " + clazz.getEnclosingConstructor());
        System.out.println("clazz.getEnclosingMethod() : " + clazz.getEnclosingMethod());
    }

    public static void main(String[] args) {
        ReflectEnclosingClassDemo demo = new ReflectEnclosingClassDemo();

        System.out.println("============= anonymous class on method =============");
//        anonymousMethod();
        Runnable runnable = new Runnable() {

            @Override
            public void run() {

            }

        };
        Class<?> clazz = runnable.getClass();
        System.out.println("class : " + clazz);
        System.out.println("class.getSuper() : " + clazz.getSuperclass());
        System.out.println("class.getInterfaces() : " + Arrays.asList(clazz.getInterfaces()));
        System.out.println("clazz.getEnclosingClass() : " + clazz.getEnclosingClass());
        System.out.println("clazz.getEnclosingConstructor() : " + clazz.getEnclosingConstructor());
        System.out.println("clazz.getEnclosingMethod() : " + clazz.getEnclosingMethod());

    }

}
