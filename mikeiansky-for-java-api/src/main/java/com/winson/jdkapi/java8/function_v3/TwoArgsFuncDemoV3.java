package com.winson.jdkapi.java8.function_v3;

import java.util.function.Consumer;

/**
 * @author mike ian
 * @date 2023/7/3
 * @desc
 **/
public class TwoArgsFuncDemoV3 {

    public static void build(Consumer<TwoArgReturnVoidFunc<String,Object>> consumer){
        TwoArgConsumer<String,Object> twoArgConsumer = new TwoArgConsumer<>();
        consumer.accept(twoArgConsumer::create);
    }


    public static void main(String[] args) {

        build(stringObjectTwoArgReturnVoidFunc -> {
//                System.out.println(stringObjectTwoArgReturnVoidFunc.getClass());
//                System.out.println(stringObjectTwoArgReturnVoidFunc.getClass());
            System.out.println("start");
            stringObjectTwoArgReturnVoidFunc.apply("key1", "value1");
            stringObjectTwoArgReturnVoidFunc.apply("key2", "value2");
            System.out.println("end");
        });

    }

}
