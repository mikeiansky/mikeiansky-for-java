package com.winson.jdkapi.collection;

import java.util.*;

/**
 * @author Mike Ian
 * @date 2023/3/2
 **/
public class RecursionDemoV1 {

    public static class Item {
        public int id;
        public int parentId;
        public String name;
    }

    public static void main(String[] args) {

        Item item1 = new Item();
        item1.id = 1;
        item1.parentId = 3;

        Item item2 = new Item();
        item2.id = 2;
        item2.parentId = 1;

        Item item3 = new Item();
        item3.id = 3;
        item3.parentId = 2;

        Map<Integer, Item> cache = new HashMap<>();
        cache.put(item1.id, item1);
        cache.put(item2.id, item2);
        cache.put(item3.id, item3);

        getWithParent(new HashMap<>(), cache, 3);
    }

    public static void getWithParent(Map<Integer, Item> result, Map<Integer, Item> cache, int id) {
        Set<Integer> safe = new HashSet<>();
        getWithParentSafe(safe, result, cache, id);

    }

    public static void getWithParentSafe(Set<Integer> safe, Map<Integer, Item> result, Map<Integer, Item> cache, int id) {
        if (safe.contains(id)) {
            return;
        }
        Item item = cache.get(id);
        if (item == null) {
            return;
        }
//        safe.add(id);
        result.put(id, item);
//        if (1 == 1){
//            throw new IllegalStateException("ccc");
//        }
        getWithParentSafe(safe, result, cache, item.parentId);
    }

}
