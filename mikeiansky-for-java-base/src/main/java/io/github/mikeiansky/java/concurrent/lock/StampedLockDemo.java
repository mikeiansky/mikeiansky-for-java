package io.github.mikeiansky.java.concurrent.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * @author mike ian
 * @date 2025/6/4
 * @desc
 **/
public class StampedLockDemo {


    public static void main(String[] args) {

        StampedLock stampedLock = new StampedLock();
        long stamp1 = stampedLock.readLock();
        System.out.println(stamp1);
        stampedLock.unlockRead(stamp1);
        long stamp2 = stampedLock.writeLock();
        System.out.println(stamp1);
        stampedLock.unlockWrite(stamp2);
        long stamp3 = stampedLock.tryOptimisticRead();
        System.out.println("stampedLock.tryOptimisticRead() : " + stamp3);
        long stamp4 = stampedLock.readLock();
        stampedLock.unlockRead(stamp4);
        System.out.println("complete ... ");

    }

}
