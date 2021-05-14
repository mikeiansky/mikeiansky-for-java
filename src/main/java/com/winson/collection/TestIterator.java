package com.winson.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author winson
 * @date 2021/5/14
 **/
public class TestIterator {

    public static void main(String[] args) {
        System.out.println(" test iterator start ... ");
        List<Integer> coll = new ArrayList<>();
        coll.add(1);
        coll.add(2);
        coll.add(3);
        coll.add(4);
        System.out.println(coll);

        Iterator<Integer> iterator = coll.iterator();
        while (iterator.hasNext()){
            Integer item = iterator.next();
            if(item == 3){
                iterator.remove();
            }
        }

        System.out.println("after remove");
        System.out.println(coll);

        System.out.println(" test iterator end ... ");
    }

}
