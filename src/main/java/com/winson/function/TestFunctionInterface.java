package com.winson.function;

import java.util.function.Function;

/**
 * @author winson
 * @date 2021/6/17
 **/
public class TestFunctionInterface {

    public static void main(String[] args) {
        System.out.println("test function interface start ... ");
        testFunction();
        System.out.println("test function interface end ...");

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

    }

}
