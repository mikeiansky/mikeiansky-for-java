package com.winson.jdkapi.collection;

import java.util.ArrayDeque;

/**
 * @author winson
 * @date 2022/4/18
 **/
public class DequeDemoV1 {

    public static void main(String[] args) {

        ArrayDeque<String> deque = new ArrayDeque<>();
        deque.push("4");
        deque.push("9");
        deque.push("20");

//        System.out.println(deque.getFirst());

        System.out.println(deque.size());
        System.out.println(deque.peek());
        System.out.println(deque.size());
        System.out.println(deque.pop());
        System.out.println(deque.size());

    }

}
