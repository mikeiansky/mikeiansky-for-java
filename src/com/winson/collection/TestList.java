package com.winson.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winson
 * @date 2020/12/10
 * @desc 集合测试类
 **/
public class TestList {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(list);
        System.out.println(list.subList(0,4));
        System.out.println(list.subList(0,list.size()));
    }

}
