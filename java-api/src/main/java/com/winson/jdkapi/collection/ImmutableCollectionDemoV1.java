package com.winson.jdkapi.collection;

import com.google.common.collect.ImmutableMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mike ian
 * @date 2023/6/1
 * @desc
 **/
public class ImmutableCollectionDemoV1 {

//    public static final Map<String, String> config = Map.ofEntries(
//            Map.entry("one", "one"),
//            Map.entry("two", "two"),
//            Map.entry("three", "three"));


    public static void main(String[] args) {

//        Map<String, String> result = Map.of("one", "one", "two", "two", "three", "three");
//        System.out.println(result);

//        result.put("four", "four");
//        System.out.println(result);

//        Map.ofEntries(Map.entry("one", "one"), Map.entry("two", "two"), Map.entry("three", "three"));

//        System.out.println(config);

        Map<String,String> rm = new HashMap<>();
        rm.put("one","one");
        rm.put("two","two");
        rm.put("three","three");
        Map<String,String> m2 = Collections.unmodifiableMap(rm);
        System.out.println(m2);
//        m2.put("four","four");


    }

}
