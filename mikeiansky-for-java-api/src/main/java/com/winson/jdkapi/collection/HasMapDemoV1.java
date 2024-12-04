package com.winson.jdkapi.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * @author winson
 * @date 2021/10/14
 **/
public class HasMapDemoV1 {

    public static class MyIterator implements Iterator<MyIterator>{

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public MyIterator next() {
            return null;
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }

        @Override
        public void forEachRemaining(Consumer<? super MyIterator> action) {
            Iterator.super.forEachRemaining(action);
        }

    }

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
//        System.out.println(3);
        // 这里可能会引起，数据resize ， 以及 node 的 tree 化
        // resize 不一定会导致 重新 tree 化， 对应的可能保持不变
        map.put("one", "one");
        map.put("two", "two");
        map.putIfAbsent("three", "three");
        // will replace value
        map.compute("one", (nk,ov)-> {
            System.out.println("compute , nk : " + nk + " , ov : " + ov);
            return nk+"Compute";
        });
        // where replace
        map.computeIfAbsent("five",key-> {
            System.out.println("computeIfAbsent key : " + key);
            return key+"Absent";
        });
        // where replace
        map.computeIfPresent("one", (nk,ov)->{
            System.out.println("computeIfPresent nv : " + nk + " , ov : " + ov);
           return nk + "NewValue";
        });
        map.merge("one", "oneMerge", (ov,nv)->{
            System.out.println("merge nv : " + nv +" , ov : " + ov);
           return nv+"AfterMerge";
        });

        map.size();
//        map.replace("","");
        map.replaceAll((key, value) -> value+"Replace");
        // 这里可以从几个地方查找，一个是hash定位到的地方，一个是被分裂后的位置
        // 问题，如果被多次分裂，会怎么处理
        map.get("one");

        // 这里的影响和put一样，可能导致node非tree化
//        map.remove("one");
        System.out.println(map);

        map.keySet();
        map.values();

//        int n = 6;
//        n |= 2;
//        n ^= 4;
//        Object o1 = null;
//        Object o2 = null;
//        System.out.println(o1 == o2);
//        System.out.println(n >>> 1);
//        System.out.println(n);

    }

}
