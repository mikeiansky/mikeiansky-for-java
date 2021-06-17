package com.winson.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author winson
 * @date 2021/6/17
 **/
public class TestFunctionInterface {

    static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println("test function interface start ... ");
//        testFunction();
        testFunctionIdentity();
        System.out.println("test function interface end ...");

    }

    private static void testFunctionIdentity() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("one"));
        personList.add(new Person("two"));
        personList.add(new Person("three"));
        personList.add(new Person("four"));

        Map<String, Person> personMap = personList.stream().collect(Collectors.toMap(Person::getName, Function.identity()));
        System.out.println("personList : " + personList);
        System.out.println("personMap : " + personMap);
    }

    public static void testFunction() {

        Function<Integer, String> function = new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return "apply - " + integer;
            }
        };

        System.out.println("function apply " + function.apply(1));
        System.out.println("function compose apply : " + function.compose(new Function<String, Integer>() {
            @Override
            public Integer apply(String source) {
                return source.length();
            }
        }).apply("abc"));

        System.out.println("function andThen apply : " + function.andThen(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return "after - str - " + s;
            }
        }).apply(3));

        System.out.println("function andThen andThen apply : " + function.andThen(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return "after - str - " + s;
            }
        }).andThen(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return "last apply : " + s;
            }
        }).apply(4));

        System.out.println(Function.identity());
        ;

    }

}
