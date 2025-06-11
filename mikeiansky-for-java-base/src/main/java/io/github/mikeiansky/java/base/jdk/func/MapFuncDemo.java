package io.github.mikeiansky.java.base.jdk.func;

import java.util.HashMap;
import java.util.function.BiConsumer;

/**
 *
 * @author mike ian
 * @date 2025/6/11
 * @desc
 **/
public class MapFuncDemo {

    public static void doMap(String k, String v, BiConsumer<String, String> func) {
        if (func != null) {
            func.accept(k, v);
        }
    }

    public static void useMap(String k, String v) {
        System.out.println("use map with key: " + k + ", value: " + v);
    }

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        doMap("key1", "value1", map::put);
        System.out.println(map);
        doMap("key2", "value2", MapFuncDemo::useMap);
    }

}
