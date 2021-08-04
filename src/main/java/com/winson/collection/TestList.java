package com.winson.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author com.winson
 * @date 2020/12/10
 * @desc 集合测试类
 **/
public class TestList {

    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        list.add(0);
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//        list.add(6);
//        list.add(7);
//        int from = 0;
//        int size = 3;
//        System.out.println(list.subList(from, list.size()));
//        System.out.println(list.subList(size, size * 2));
//        System.out.println(list);
//        System.out.println(list.subList(0,4));
//        System.out.println(list.subList(0,list.size()));
//        list.remove((Integer) 7);
//        System.out.println(list);
//        list = list.stream().filter(id->id>4).collect(Collectors.toList());
//        System.out.println(list);

//        int[] ints3 = new int[]{3, 102};
//        List<int[]> list = Arrays.asList(ints3);

//        List<String> mobileList = new ArrayList<>();
//        mobileList.add("2");
//        mobileList.add("3");
//        mobileList.add("6");
//        mobileList.add("7");
//        mobileList.add("10");

//        List<String> result = mobileList.stream().distinct().collect(Collectors.toList());
//        System.out.println(result);

//        System.out.println(mobileList.subList(2, 4));

//        List<Integer> list = null;

//        List<Integer> datas = new ArrayList<>();
//        datas.add(1);
//        datas.add(2);
//        datas.add(3);
//        System.out.println(datas);
//        datas.addAll(list);

//        Integer aa = null;
//        int[] arr = new int[]{1,2,3,4,5};
//        int[] arr2 = null;
//        System.out.println(arr[5]);
//        System.out.println(arr[aa]);

        ArrayList list = new ArrayList<Integer>();
        list.add(2);
        System.out.println(list);
        System.out.println("1");
        list.add("winson");
        System.out.println(list);

    }

}
