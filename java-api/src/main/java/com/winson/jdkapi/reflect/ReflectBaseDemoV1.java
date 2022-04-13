package com.winson.jdkapi.reflect;

import java.util.Arrays;

/**
 * @author winson
 * @date 2021/9/14
 **/
public class ReflectBaseDemoV1 {

    public interface Person {

    }

    public static class Man implements Person{

    }

    public static void main(String[] args) {
        for (Class<?> anInterface : Man.class.getInterfaces()) {
            System.out.println(anInterface);
        }
        System.out.println(Arrays.asList(Man.class.getInterfaces()));
        Integer.valueOf(1000);
        Integer.valueOf(1001);
        Integer.valueOf(-128);
        Integer.valueOf(-200);
        Integer.valueOf(-1001);

        String[] arr = new String[]{"one","two"};
        System.out.println(arr.getClass().getTypeName());

        System.out.println(arr.getClass().isArray());
        System.out.println(Man.class.getConstructors()[0].getParameterCount());
    }

}
