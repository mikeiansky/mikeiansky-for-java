package com.winson.jdkapi.reflect;

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
        System.out.println(Man.class.getInterfaces());
        Integer.valueOf(1000);
        Integer.valueOf(1001);
        Integer.valueOf(-128);
        Integer.valueOf(-200);
        Integer.valueOf(-1001);
    }

}
