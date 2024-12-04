package com.winson.jdkapi.collection;

import java.util.HashMap;

/**
 * @author winson
 * @date 2022/2/23
 **/
public class HashMapRemoveThreadDemoV1 {

    public static HashMap<Integer, Boolean> map = new HashMap<>();

    public static void testRemove(int id) {
        System.out.println("testRemove 1 id : " + id);
        Boolean removeResult = map.remove(id);
        System.out.println("testRemove 2 id : " + id + " , removeResult : " + removeResult);
    }

    public static void main(String[] args) {
        final int one = 2449527;
        final int two = 2449528;
        final int three = 2449529;
        System.out.println(map.remove(two));
        System.out.println(map.remove(three));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                testRemove(one);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                testRemove(two);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                testRemove(three);
            }
        }).start();

        boolean flag = true;
        while (flag){

        }

    }

}
