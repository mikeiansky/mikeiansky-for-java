package com.winson.jdkapi.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mike Ian
 * @date 2022/10/14
 **/
public class CollectionOpDemoV1 {

    public static void main(String[] args) {

        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        idList.add(3);
        idList.add(4);
        idList.add(5);
        idList.add(6);

        System.out.println(idList.subList(1,20));

    }

}
