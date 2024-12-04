package com.winson.jdkapi.java8.function_v3;


/**
 * @author mike ian
 * @date 2023/7/3
 * @desc
 **/
public class TwoArgConsumer<K, V> {

    public String create(K key, V value) {
        System.out.println("create key : " + key + " , value : " + value);
        return "success";
    }

}
