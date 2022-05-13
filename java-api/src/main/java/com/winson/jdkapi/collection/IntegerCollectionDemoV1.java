package com.winson.jdkapi.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winson
 * @date 2022/5/13
 **/
public class IntegerCollectionDemoV1 {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(8);
        list.add(2);
        list.add(1);
        System.out.println(list);
        list.remove(Integer.valueOf(8));
        System.out.println(list);
        list.remove(Integer.valueOf(1));
        System.out.println(list);

    }

}
