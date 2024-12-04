package com.winson.jdkapi.java8.function_v3;

/**
 * @author mike ian
 * @date 2023/7/5
 * @desc
 **/
public class MethodFuncDemoV3 {

    public static <T,S> Create<T,S> generateCreate(T t){
        return MethodFuncDemoV3::realCreate;
    }

    public static <T,S> S realCreate(T t){
        System.out.println("real create : " + t);
        return null;
    }

    @FunctionalInterface
    public interface Create<T,S>{
        S apply(T t);
    }

    public static void main(String[] args) {
        Create<String, Integer> create = generateCreate("hello");
        create.apply("world");
    }

}
