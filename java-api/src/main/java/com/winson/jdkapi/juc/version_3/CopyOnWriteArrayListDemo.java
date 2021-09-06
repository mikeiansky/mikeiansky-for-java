package com.winson.jdkapi.juc.version_3;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author winson
 * @date 2021/6/21
 **/
public class CopyOnWriteArrayListDemo {



    public static void main(String[] args) {

        ArrayList<String> sourceList = new ArrayList<>();
        sourceList.add("one");
        sourceList.add("two");
        sourceList.add("three");
        sourceList.add("four");
        System.out.println(" -------- source list -------- ");
        System.out.println(sourceList);
        CopyOnWriteArrayList<String> copyList = new CopyOnWriteArrayList<>(sourceList);
        System.out.println(" -------- copy on write list -------- ");
        System.out.println(copyList);
        copyList.add("five");
        System.out.println(copyList);

    }

}
