package com.winson.jdkapi.reflect.v1;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author winson
 * @date 2021/10/9
 **/
public class InterfaceMethodDemoV1 {

    public interface Face1 {

        void normalFace();

        default void faceToFace() {

        }
    }

    public static class NormalFace implements Face1 {

        @Override
        public void normalFace() {

        }

    }

    public static void main(String[] args) {

        System.out.println(" =========== clazz methods ===========");
        Class<?> clazz = NormalFace.class;
        for (Method method : clazz.getMethods()) {
            System.out.println("isAbstract : " + Modifier.isAbstract(method.getModifiers()) + " , name : " + method.getName());
        }
        System.out.println(" =========== declared method ===========");
        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            System.out.println("isAbstract : " + Modifier.isAbstract(declaredMethod.getModifiers()) + " , name : " + declaredMethod.getName());
        }
        System.out.println("=========== interface default method ============");
        for (Class<?> anInterface : clazz.getInterfaces()) {
            for (Method declaredMethod : anInterface.getDeclaredMethods()) {
                System.out.println("isAbstract : " + Modifier.isAbstract(declaredMethod.getModifiers()) + " , name : " + declaredMethod.getName() + " , declared class : " + anInterface.getDeclaringClass());
            }
        }
    }

}
