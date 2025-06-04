package io.github.mikeiansky.java.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mike ian
 * @date 2025/6/4
 * @desc
 **/
public class ReentrantLockDemo {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();

        lock.tryLock();
        System.out.println("run @ lock body");
        lock.unlock();

        System.out.println("run @ main method");
    }

}
