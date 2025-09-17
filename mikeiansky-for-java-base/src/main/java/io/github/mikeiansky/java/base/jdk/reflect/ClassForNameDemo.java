package io.github.mikeiansky.java.base.jdk.reflect;

import java.lang.reflect.InvocationTargetException;

public class ClassForNameDemo {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        System.out.println("hello world");
        Class clazz = Class.forName("io.github.mikeiansky.java.base.jdk.reflect.BaseReflectDemo");
        System.out.println(clazz);
        System.out.println(clazz.getConstructor().newInstance());

    }

}
