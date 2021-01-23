package com.winson.reflect;

import com.winson.net.TestNet;
import org.junit.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author winson
 * @date 2021/1/23
 **/
public class TestReflect {

    public static void target(int i) {
        new Exception("#" + i).printStackTrace();
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

//        Class klass = TestReflect.class;
//        Method method = klass.getMethod("target", int.class);
//        for (int i = 0; i < 20; i++) {
//            method.invoke(null, i);
//        }

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType methodType = MethodType.methodType(String.class);
        MethodHandle methodHandle = lookup.findStatic(TestNet.class,"getIpAddress",methodType);
        System.out.println(methodHandle);
        try {
            String result = (String) methodHandle.invoke();
            System.out.println(result);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
