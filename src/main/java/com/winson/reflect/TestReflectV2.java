package com.winson.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author winson
 * @date 2021/6/15
 **/
public class TestReflectV2 {

    public static void main(String[] args) {
        System.out.println("test reflect v2 start ... ");

        System.out.println("----------- Son#getMethods ---------- ");
        Method[] methods = Son.class.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        System.out.println("----------- Son#getDeclaredMethods ---------- ");
        Method[] declaredMethods = Son.class.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println(method.getName());
        }

        System.out.println("-----------  Son#getFields ---------- ");
        Field[] fields = Son.class.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        System.out.println("-----------  Son#getDeclaredFields ---------- ");
        Field[] declaredFields = Son.class.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field.getName() + " , modifiers : " + field.getModifiers());
        }
        System.out.println("-----------  Son#other info ----------- ");
        System.out.println("class loader : " + Son.class.getClassLoader());
        System.out.println("-----------  getInterfaces ----------- ");
        Class[] sonInterfaces = Son.class.getInterfaces();
        for (Class sonInterface : sonInterfaces) {
            System.out.println(sonInterface);
        }

        System.out.println("test reflect v2 stop ... ");
    }

}
