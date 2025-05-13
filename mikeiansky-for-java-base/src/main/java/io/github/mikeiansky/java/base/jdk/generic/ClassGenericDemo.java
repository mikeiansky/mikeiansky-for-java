package io.github.mikeiansky.java.base.jdk.generic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author mike ian
 * @date 2025/5/13
 * @desc
 **/
public class ClassGenericDemo {

    public static class One {

    }

    public static class Two {

    }

    public static class Three {

    }

    public static <T, R> T createObject(Class<T> clazz) throws Exception {
        Constructor<T> constructor = (Constructor<T>) clazz.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        return constructor.newInstance();
    }

    public static <T> void useObject(Class<T> clazz, T obj) throws Exception {
        System.out.println("clazz : " + clazz.getName());
        System.out.println("obj : " + obj);
    }

    public static void main(String[] args) throws Exception {
        One one = createObject(One.class);
        System.out.println(one);
        useObject(One.class, one);
        Two two = createObject(Two.class);
        System.out.println(two);
        useObject(Two.class, two);
    }

}
