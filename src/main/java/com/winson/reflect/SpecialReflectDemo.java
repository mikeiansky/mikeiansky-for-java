package com.winson.reflect;

import java.lang.reflect.Field;

/**
 * @author winson
 * @date 2021/6/28
 **/
public class SpecialReflectDemo {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {

        ReflectObject obj = new ReflectObject();

        Class clazz = ReflectObject.class;

        Field finalField = clazz.getDeclaredField("privateInt");
        finalField.setAccessible(true);
        System.out.println(finalField.get(obj));
        finalField.set(obj, 3);
        System.out.println("after set filed value ----------- ");
        System.out.println(finalField.get(obj));

    }

}
