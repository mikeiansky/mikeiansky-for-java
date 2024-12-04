package com.winson.jdkapi.collection;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author winson
 * @date 2021/10/15
 **/
public class ConcurrentHashMapDemoV1 {

    public static void main(String[] args) {

//        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
//
//        map.put("one", "one");
//        map.put("two", "two");
//        map.put("three", "three");
//        map.put("four", "four");
//        map.remove("five");
//
//        map.get("one");
//
//        map.size();

        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(3);
        list.add(234);
        list.add(1);
        list.indexOf(11);
        list.remove((Integer)234);



        System.out.println(list);

//        Integer i3 = 3;
//        System.out.println(Integer.getInteger("3"));
//        list.remove(i3);
//        System.out.println(list);

//        System.out.println(list);
//        System.out.println(list.subList(1,3));

    }

}
