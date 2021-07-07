package com.winson.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author winson
 * @date 2021/7/6
 * @desc null数据在数组中间的测试
 **/
public class NullObjectInMiddleCollectionDemo {

    public static void main(String[] args) {

        List<List<String>> list = new ArrayList<>();

        List<String> c1 = new ArrayList<>();
        list.add(c1);

        List<String> c2 = new ArrayList<>();
        list.add(c2);

        list.add(null);

        List<String> c3 = new ArrayList<>();
        list.add(c3);

        System.out.println(list);

        // throw NullPointerException
//        List<String> result = list.stream().flatMap(Collection::stream).collect(Collectors.toList());
//        System.out.println(result);

        List<String> result = list.stream().filter(Objects::nonNull).flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(result);

    }

}
