package com.winson.optional;

import java.util.Optional;

/**
 * @author winson
 * @date 2021/3/30
 **/
public class TestOptional {

    public static class Person {
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Person p = new Person();
        System.out.println(p);
//        p.name = "winson";
        String result = Optional.ofNullable(p)
                .map(Person::getName).orElse("null object.");
        System.out.println(result);

        Optional.ofNullable(p)
                .ifPresent(pp -> {
                    System.out.println("p exists" + pp);
                });


    }

}
