package com.winson.jdkapi.reflect.v1;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author winson
 * @date 2021/10/5
 **/
public class InterfaceDemoV1 {

    interface InterfaceOne {

    }

    interface InterfaceTwo {

    }

    interface InterfaceThree extends InterfaceOne{

    }

    interface InterfaceFour extends InterfaceOne, InterfaceTwo{

    }

    interface InterfaceFive extends InterfaceFour, InterfaceThree{

    }

    public static void main(String[] args) {
        Class clazz = InterfaceFive.class;
        System.out.println(clazz.getName());
        System.out.println(clazz.getCanonicalName());
        System.out.println(clazz.getSuperclass());
        System.out.println(Arrays.asList(clazz.getInterfaces()));
        System.out.println("====== interfaces ======");
        for (Class cif : clazz.getInterfaces()) {
            System.out.println("isInterface : "+cif.isInterface() + " , " + cif + " , interfaces : " + Arrays.asList(cif.getInterfaces()) + " , super : " + cif.getSuperclass());
        }
    }

}
