package com.winson.jdkapi.rmi.v1.rmi;

/**
 * @author winson
 * @date 2021/12/28
 **/
public class ExtendDemo {

    public static class Person {

    }

    public static class Student extends Person{

    }

    public static void main(String[] args) {

        Person person = new Student();
        System.out.println(person.getClass());

    }

}
