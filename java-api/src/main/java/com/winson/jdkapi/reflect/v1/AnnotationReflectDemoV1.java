package com.winson.jdkapi.reflect.v1;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author winson
 * @date 2021/10/5
 **/
public class AnnotationReflectDemoV1 {

    public static void main(String[] args) {

        Class clazz = ReflectAnnotation.class;
        System.out.println("clazz.getName() : " + clazz.getName());
        System.out.println("clazz.getInterfaces() : " + Arrays.stream(clazz.getInterfaces()).collect(Collectors.toList()));
        System.out.println("clazz.getSuperclass() : " + clazz.getSuperclass());
        for (Method method : clazz.getMethods()) {
            System.out.println(method.getName());
        }

    }

}
