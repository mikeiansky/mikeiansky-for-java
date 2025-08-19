package io.github.mikeiansky.java.base.jdk.collect;

import java.util.WeakHashMap;

public class WeakHashMapDemo {


    public static void main(String[] args) {

        WeakHashMap<String, String> weakHashMap = new WeakHashMap<>();
        weakHashMap.put("1", "1");
        weakHashMap.put("2", "2");
        System.out.println(weakHashMap.get("1"));
        System.out.println(weakHashMap.get("2"));

    }

}
