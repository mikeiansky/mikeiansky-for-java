package com.winson.collection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author winson
 * @date 2021/3/27
 **/
public class TestCollectSort {

    public static void main(String[] args) {
        System.out.println("---------- 1 ----------");

        List<String> strList = new ArrayList<>();
        strList.add("2020-08-01 00:12:33");
        strList.add("2020-08-03 10:12:33");
        strList.add("2020-05-01 00:12:33");
        strList.add("2021-04-01 02:12:33");
        strList.add("2020-08-01 00:13:33");
        strList.add("2020-08-01 00:13:34");
        strList.add("2021-01-01 03:15:37");

        strList.sort(Comparator.comparing(o1-> o1));

        for (String s : strList) {
            System.out.println(s);
        }

        System.out.println("---------- 2 ----------");
    }

}
