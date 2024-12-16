package io.github.mikeiansky.java.base.jdk.collect;

import java.util.ArrayDeque;

/**
 * @author mike ian
 * @date 2024/12/16
 * @desc
 **/
public class ArrayDequeDemo {

    public static void main(String[] args) {
        ArrayDeque<String> deque = new ArrayDeque<>();

        deque.add("1");
        deque.add("2");
        deque.add("3");

        System.out.println(deque);
        System.out.println(deque.removeFirst());
        System.out.println(deque);
        System.out.println(deque.removeLast());
        System.out.println(deque);


    }

}
