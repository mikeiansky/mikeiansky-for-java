package com.winson.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winson
 * @date 2021/2/1
 **/
public class TestJava8Stream {

    public static void main(String[] args) {

        List<Integer> countList = new ArrayList<>();
        countList.add(34);
        countList.add(23);
        countList.add(12);
        countList.add(55);
        countList.add(42);
        System.out.println(countList);

        System.out.println(countList.stream().findFirst().get());

    }

}
