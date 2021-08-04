package com.winson.app;

import org.apache.activemq.util.LRUCache;

import java.util.LinkedHashMap;

/**
 * @author winson
 * @date 2021/7/30
 **/
public class LruCacheDemo {

    public static void main(String[] args) {

//        LRUCache<String, String> lruCache = new LRUCache<>();
//        int size = 100000;
//        for (int i = 0; i <= size; i++) {
//            lruCache.put("winson" + i, "winson" + i);
//        }
//        System.out.println(lruCache.getMaxCacheSize());
//        System.out.println(lruCache.get("winson1"));
//        System.out.println(lruCache.get("winson10"));
//        System.out.println(lruCache.get("winson100"));
//        System.out.println(lruCache.get("winson1000"));
//        System.out.println(lruCache.get("winson10000"));
//        System.out.println(lruCache.get("winson100000"));
//        System.out.println("end");

        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("one1", "one");
        map.put("one2", "one");
        map.put("one3", "one");
        map.put("one4", "one");
        System.out.println(map);
        System.out.println(map.size());

    }

}
