package com.winson.jdkapi.reflect.v1;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author winson
 * @date 2021/10/8
 **/
public class ReflectMethodEqualsPredicateDemoV3 {


    public static void main(String[] args) {
        Class<?> clazz = ReflectMethodEqualsPredicateDemoV3.class;
        Map<Method, String> methodMap = new HashMap<>();
        Method method1 = null;
        Method method2 = null;
        try {
            method1 = clazz.getMethod("main", String[].class);
            methodMap.put(method1, "method-1");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            method2 = clazz.getMethod("main", String[].class);
            methodMap.put(method2, "method-2");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println("method1 == method2 : " + (method1 == method2));
        System.out.println("method1.equals(method2) : " + (method1.equals(method2)));
        System.out.println("methodMap.get(method1) : " + methodMap.get(method1));
        System.out.println("methodMap.get(method2) : " + methodMap.get(method2));

    }

}
