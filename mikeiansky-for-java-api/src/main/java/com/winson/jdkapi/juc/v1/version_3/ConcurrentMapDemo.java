package com.winson.jdkapi.juc.v1.version_3;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author winson
 * @date 2021/6/21
 **/
public class ConcurrentMapDemo {

    public static void main(String[] args) {

        runWithConcurrentHashMap();
        runWithConcurrentSkipListMap();

    }

    private static void runWithConcurrentSkipListMap() {
        System.out.println("normal concurrent skip list map");
        ConcurrentSkipListMap<String,String> map = new ConcurrentSkipListMap<>();
        map.put("two", "two");
        map.put("one", "one");
        map.put("three", "three");
//        map.put(null,"null"); // exception
//        map.put("null",null); // exception
        System.out.println(map);
        System.out.println(map.keySet());
    }

    private static void runWithConcurrentHashMap() {
        System.out.println("normal concurrent hash map");

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("two", "two");
        map.put("one", "one");
        map.put("three", "three");
//        map.put(null,"null"); // exception
//        map.put("null",null); // exception
        System.out.println(map);
        System.out.println(map.keySet());

    }

}
