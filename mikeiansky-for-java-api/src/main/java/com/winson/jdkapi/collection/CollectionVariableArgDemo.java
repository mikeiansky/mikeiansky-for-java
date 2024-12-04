package com.winson.jdkapi.collection;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author winson
 * @date 2022/4/25
 **/
public class CollectionVariableArgDemo {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        list.add("one");
        list.add("five");
        list.add("seven");

        String[] test = new String[]{"two", "four"};
//        list.addAll(test);
        Collections.addAll(list, test);
        System.out.println(list);

    }

}
