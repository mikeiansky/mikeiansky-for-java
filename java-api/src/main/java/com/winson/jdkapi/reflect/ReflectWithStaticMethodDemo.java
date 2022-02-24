package com.winson.jdkapi.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author winson
 * @date 2022/2/24
 **/
public class ReflectWithStaticMethodDemo {

    public static void testStatic(String msg){
        System.out.println("ReflectWithStaticMethodDemo :: testStatic :: " + msg);
    }

    public void sayHello(String msg){
        System.out.println("ReflectWithStaticMethodDemo :: sayHello :: " + msg);

    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        Class clazz = ReflectWithStaticMethodDemo.class;

        Method sayHello = clazz.getMethod("sayHello",new Class[]{String.class});
        sayHello.invoke(new ReflectWithStaticMethodDemo(), "winson");

        Method testStatic = clazz.getMethod("testStatic",new Class[]{String.class});
        testStatic.invoke(null, "winson 1 ");
        testStatic.invoke(new ReflectWithStaticMethodDemo(), "winson 2 ");

    }

}
