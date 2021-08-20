package com.winson.jdkapi.java8;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author winson
 * @date 2021/5/28
 **/
public class TestCollectionJoinToString {

    public static void main(String[] args) {
        System.out.println("test collection join to string 1");
        List<Integer> idList = Arrays.asList(1,2,3,4,5,7);

        System.out.println(idList.stream().map(Object::toString).collect(Collectors.joining(",")));

//        String t = String.join(",", "c", "bbb");
//        System.out.println(t);
//        String result = String.join(",",Arrays.asList(1,3,4,5,6,7).stream().map(Object::toString).collect(Collectors.toList()));
//        System.out.println(result);
//
//        System.out.println("wait at : " + new Date());

        System.out.println("test collection join to string 2");
    }

}
