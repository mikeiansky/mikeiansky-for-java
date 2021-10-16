package com.winson.jdkapi.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author winson
 * @date 2021/10/15
 **/
public class StreamDemoV1 {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);

        Object result = list.stream()
                .collect(Collectors.toList());

        System.out.println(result);

    }

    public static boolean getTrue(int a){
        return true;
    }

}
