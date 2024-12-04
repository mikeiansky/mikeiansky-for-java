package com.winson.jdkapi.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author winson
 * @date 2022/1/27
 **/
public class StreamDemo {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

//        list.stream().forEach(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) {
//                System.out.println(integer);
//            }
//        });

//        list.stream().map(new Function<Integer, Integer>() {
//            @Override
//            public Integer apply(Integer integer) {
//                System.out.println(integer);
//                return integer;
//            }
//        }).collect(Collectors.toList());

        list.stream().map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                System.out.println("apply - "+integer);
                return integer;
            }
        }).forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("accept - "+integer);
            }
        });

    }

}
