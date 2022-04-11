package com.winson.jdkapi.reflect_v2;

import com.winson.utils.common.PrintUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author winson
 * @date 2022/4/11
 **/
public class ForMethodInfoDemoV1 {

    public static void main(String[] args) {
        Class clazz = TargetObj.class;
        System.out.println("===== declared method =====");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            displayMethod(method);
        }
    }

    public static void displayMethod(Method method) {
        System.out.println("method:" + method);
        System.out.println("method.getName():" + method.getName());
        System.out.println("method.getDeclaringClass():" + method.getDeclaringClass());
        System.out.println("method.getParameterCount():" + method.getParameterCount());
        System.out.println("method.getParameterTypes():" + Arrays.stream(Optional.ofNullable(method.getParameterTypes())
                .orElse(new Class[0])).collect(Collectors.toList()));
        System.out.println("method.getReturnType():" + method.getReturnType());
        System.out.println("method.getGenericReturnType():" + method.getGenericReturnType());
        System.out.println("method.getGenericParameterTypes():" + PrintUtils.toList(method.getGenericParameterTypes()));
        System.out.println("method.getTypeParameters():" + PrintUtils.toList(method.getTypeParameters()));
        System.out.println("method.getExceptionTypes():" + PrintUtils.toList(method.getExceptionTypes()));
        System.out.println("method.getAnnotations():" + PrintUtils.toList(method.getAnnotations()));
        System.out.println("method.getDeclaredAnnotations():" + PrintUtils.toList(method.getDeclaredAnnotations()));
        System.out.println("method.getAnnotatedReturnType():" + method.getAnnotatedReturnType());
        System.out.println("method.isBridge():" + method.isBridge());
        System.out.println("method.isSynthetic():" + method.isSynthetic());
        System.out.println("method.isDefault():" + method.isDefault());
        System.out.println("method.isVarArgs():" + method.isVarArgs());
        System.out.println("method.isAccessible():" + method.isAccessible());
        System.out.println("method.getModifiers():" + method.getModifiers());
        System.out.println("method.getDefaultValue():" + method.getDefaultValue());
        System.out.println();
    }

}
