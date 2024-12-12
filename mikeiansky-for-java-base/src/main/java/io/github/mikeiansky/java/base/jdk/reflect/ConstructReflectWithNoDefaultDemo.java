package io.github.mikeiansky.java.base.jdk.reflect;

import java.lang.reflect.Constructor;

/**
 * @author mike ian
 * @date 2024/12/12
 * @desc
 **/
public class ConstructReflectWithNoDefaultDemo {

    public static class One {
//        public One(){
//
//        }

        public One(String tag) {

        }

    }

    public static void main(String[] args) {

        try {
            Constructor noParamConstructor = One.class.getDeclaredConstructor();
            System.out.println("noParamConstructor :: " + noParamConstructor);
        } catch (NoSuchMethodException e) {
            System.out.println("getDeclaredConstructor noParamConstructor :: NoSuchMethodException ");
        }
        try {
            Constructor tagConstructor = One.class.getDeclaredConstructor(String.class);
            System.out.println("tagConstructor :: " + tagConstructor);
        } catch (NoSuchMethodException e) {
            System.out.println("getDeclaredConstructor tagConstructor :: NoSuchMethodException ");
        }
        try {
            Constructor objectConstructor = One.class.getDeclaredConstructor(Object.class);
            System.out.println("objectConstructor :: " + objectConstructor);
        } catch (NoSuchMethodException e) {
            System.out.println("getDeclaredConstructor objectConstructor :: NoSuchMethodException ");
        }
    }

}
