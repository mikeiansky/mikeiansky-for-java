package com.winson.jdkapi.collection;


import java.util.concurrent.ConcurrentHashMap;

/**
 * @author winson
 * @date 2021/10/15
 **/
public class ConcurrentHashMapDemoV1 {

    public static void main(String[] args) {

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

        map.put("one", "one");
        map.put("two", "two");
        map.put("three", "three");
        map.put("four", "four");
        map.remove("five");

        map.get("one");

        map.size();

    }

}
