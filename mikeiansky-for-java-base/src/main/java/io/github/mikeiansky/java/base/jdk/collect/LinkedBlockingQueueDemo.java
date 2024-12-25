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
        queue.offer(33);
        System.out.println("after offer ... ");
        System.out.println(queue.poll());
        System.out.println(queue.take());
        System.out.println("complete ... ");


    }

}
