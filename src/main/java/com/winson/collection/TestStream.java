package com.winson.collection;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author winson
 * @date 2021/7/2
 **/
public class TestStream {

    public static void main(String[] args) {

        Map<String, List<Integer>> map = new HashMap<>();

        List<Integer> list1 = new ArrayList<>();
        list1.add(31);
        list1.add(25);
        list1.add(366);

        List<Integer> list2 = new ArrayList<>();
        list2.add(14);
        list2.add(54);
        list2.add(26);

        List<Integer> list3 = new ArrayList<>();
        list3.add(7);
        list3.add(58);
        list3.add(94);

        map.put("1", list1);
        map.put("2", list2);
        map.put("3", list3);

        List<Integer> result = map.values().stream().flatMap(Collection::stream).sorted().collect(Collectors.toList());
        System.out.println(result);



    }

}
