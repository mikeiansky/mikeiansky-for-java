package com.winson.jdkapi.reflect_v2;

import com.winson.utils.common.PrintUtils;

import java.lang.reflect.Field;

/**
 * @author winson
 * @date 2022/4/11
 **/
public class ForFieldDemoV1 {

    public static void main(String[] args) {

        Class clazz = TargetObj.class;
        displayFieldInfo(clazz);
    }

    public static void displayFieldInfo(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("field:" + field);
            System.out.println("field.getName():" + field.getName());
            System.out.println("field.getAnnotatedType():" + field.getAnnotatedType());
            System.out.println("field.getGenericType():" + field.getGenericType());
            System.out.println("field.getType():" + field.getType());
            System.out.println("field.getModifiers():" + field.getModifiers());
            System.out.println("field.getDeclaringClass():" + field.getDeclaringClass());
            System.out.println("field.getDeclaredAnnotations():" + PrintUtils.toList(field.getDeclaredAnnotations()));
            System.out.println("field.getAnnotations():" + PrintUtils.toList(field.getAnnotations()));
            System.out.println("field.isSynthetic():" + field.isSynthetic());
            System.out.println("field.isEnumConstant():" + field.isEnumConstant());
            System.out.println("field.isAccessible():" + field.isAccessible());
            System.out.println();
        }
    }

}
