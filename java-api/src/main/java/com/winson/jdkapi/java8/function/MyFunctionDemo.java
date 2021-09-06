package com.winson.jdkapi.java8.function;

/**
 * @author winson
 * @date 2021/6/18
 **/
public class MyFunctionDemo {

    public static void main(String[] args) {
        System.out.println("my function demo start ... ");

        MyFunction<String,Integer> myFunction = new MyFunction<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length() + 2;
            }
        };
        System.out.println(myFunction.apply("abc"));
        System.out.println(myFunction.compose(new MyFunction<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return "apply - " + integer;
            }
        }).apply(3));
        System.out.println(myFunction.andThen(new MyFunction<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return "result : " + integer;
            }
        }).apply("efgh"));

        System.out.println("my function demo end ... ");
    }

}
