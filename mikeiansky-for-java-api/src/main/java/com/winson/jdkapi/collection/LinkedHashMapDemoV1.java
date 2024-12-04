package com.winson.jdkapi.collection;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author winson
 * @date 2021/10/15
 **/
public class LinkedHashMapDemoV1 {

    public static void main(String[] args) {

        LinkedHashMap<String,String> map = new LinkedHashMap<String,String>(16,0.8f, true){

            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                System.out.println("oldest : " + eldest);
                return size() > 3;
            }

        };

        map.put("one", "one");
        map.put("two", "two");
        map.put("three", "three");
        map.put("four", "four");
        map.put("three", "three");
        map.put("five", "five");
        map.put("three", "three");
        map.put("six", "six");
        map.put("seven", "seven");

        System.out.println(map);

    }

}
