package com.winson.springboot02;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mike ian
 * @date 2023/6/5
 * @desc
 **/
public class SimpleDemo {

    public static void main(String[] args) {

        List<Integer> coll = new ArrayList<>();
        coll.add(1);
        coll.add(2);


        List<Integer> coll2 = new ArrayList<>();
        coll2.add(5);
        coll2.add(6);

        List<Integer> coll3 = new ArrayList<>();

        coll.addAll(coll2);
        coll.addAll(coll3);
        coll.addAll(coll3);

        System.out.println(coll);


    }

}
