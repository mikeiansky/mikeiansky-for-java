package com.winson.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @author winson
 * @date 2020/12/19
 **/
public class TestMap {

    public static void main(String[] args) {
        Map<Integer,String> cache = new HashMap<>();
        cache.put(Integer.parseInt("1"),"hello");
        System.out.println(cache.get(1));
        System.out.println(cache.get(Integer.parseInt("1")));

    }

}
