package io.github.mikeiansky.java.base.jdk.collect;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author mike ian
 * @date 2024/12/25
 * @desc
 **/
public class LinkedBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
//        System.out.println(queue.poll());
//        System.out.println(queue.take());
        System.out.println("start ... ");

        queue.offer(11);
        queue.offer(22);
        queue.put(33);
        queue.add(44);

        System.out.println("after offer ... ");
        System.out.println(queue.poll());
        System.out.println(queue.take());

        System.out.println("before poll");
        System.out.println(queue.poll());
        System.out.println("after poll");
        System.out.println("before take");
        System.out.println(queue.take());
        System.out.println("after take");

        System.out.println("complete ... ");


    }

}
