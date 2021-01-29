package com.winson.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author com.winson
 * @date 2020/12/10
 * @desc 集合测试类
 **/
public class TestList {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
//        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
//        int from = 0;
//        int size = 3;
//        System.out.println(list.subList(from, list.size()));
//        System.out.println(list.subList(size, size * 2));
//        System.out.println(list);
//        System.out.println(list.subList(0,4));
//        System.out.println(list.subList(0,list.size()));
//        list.remove((Integer) 7);
//        System.out.println(list);
        list = list.stream().filter(id->id>4).collect(Collectors.toList());
        System.out.println(list);
    }

}
