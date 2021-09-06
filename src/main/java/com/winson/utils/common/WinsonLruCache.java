package com.winson.utils.common;

import sun.misc.LRUCache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * @author winson
 * @date 2021/9/6
 **/
public class WinsonLruCache<K, V> {

    public static class CacheNode<K, V> {
        private CacheNode<K, V> prev;
        private CacheNode<K, V> next;
        private final K key;
        private final V value;

        public CacheNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    private HashMap<K, CacheNode<K, V>> cache = new LinkedHashMap<>();
    private CacheNode<K, V> first;
    private CacheNode<K, V> last;

    public void print() {
        if (first != null) {
            printNode(first);
        }
    }

    public void printNode(CacheNode<K, V> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.key + " : " + node.value);
        printNode(node.next);
    }

    public void add(K k, V v) {
        CacheNode<K, V> value = cache.get(k);
        if (value == null) {
            value = new CacheNode<>(k, v);
            cache.put(k, value);
        }
        if (cache.size() <= 1) {
            first = value;
            last = value;
            value.prev = null;
            value.next = null;
            return;
        }
        if(cache.size() == 2){
            if(first != value){
                last = first;
                first = value;
                last.prev = first;
                last.next = null;
                first.prev = null;
                first.next = last;
            }
            return;
        }

        if (last == value) {
            CacheNode<K, V> tempPrev = value.prev;
            tempPrev.next = null;
            last = tempPrev;
        } else {
            if (first != value) {
                if(value.prev != null){
                    value.prev.next = value.next;
                }
                if(value.next != null){
                    value.next.prev = value.prev;
                }
                value.next = first;
                first.prev = value;
                first = value;
            }

        }

    }

    public V get(K key) {
        CacheNode<K, V> value = cache.get(key);
        if (value == null) {
            return null;
        }
        if (cache.size() <= 1) {
            return value.value;
        }

        CacheNode<K, V> prev = value.prev;
        CacheNode<K, V> next = value.next;

        if (last == value) {
            last = value.prev;
            last.next = null;
        } else {
            value.prev.next = value.next;
        }

        if (first != value) {
            value.prev = null;
            value.next = first;
            first = value;
        }

        return value.value;
    }

    public static <K, V> void printCache(WinsonLruCache<K, V> cache, String flag) {
        System.out.println("--------- " + flag + " ---------");
        cache.print();
    }

    public static void main(String[] args) {
        WinsonLruCache<String, String> cache = new WinsonLruCache<>();
        cache.add("1", "1");
        cache.add("2", "2");
        cache.add("3", "3");
        cache.add("4", "4");
        cache.add("5", "5");
        printCache(cache, "initial");
        cache.get("3");
        cache.get("1");
        printCache(cache, "initial");


    }

}
