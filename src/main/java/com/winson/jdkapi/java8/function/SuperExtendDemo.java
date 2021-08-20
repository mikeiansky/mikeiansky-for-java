package com.winson.jdkapi.java8.function;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winson
 * @date 2021/6/18
 **/
public class SuperExtendDemo {

    public class Animal {

    }

    public class Person extends Animal {

    }

    public class Student extends Person {

    }

    public interface Entrance<T> {

        void input(List<? extends T> t);

        void output(List<? super T> t);

    }

    public static class MyEntrance implements Entrance<Person> {

        @Override
        public void input(List<? extends Person> t) {

        }

        @Override
        public void output(List<? super Person> t) {

        }

    }

    public static void main(String[] args) {
        System.out.println("super extend demo start ... ");
        List<Student> inputList = new ArrayList<>();
        List<Animal> outputList = new ArrayList<>();
        MyEntrance myEntrance = new MyEntrance();
        myEntrance.input(inputList);
        myEntrance.output(outputList);
        System.out.println("super extend demo end ... ");
    }

}
