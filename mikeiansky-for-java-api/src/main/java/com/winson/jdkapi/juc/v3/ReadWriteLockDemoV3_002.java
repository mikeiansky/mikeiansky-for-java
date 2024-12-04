package com.winson.jdkapi.juc.v3;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Mike Ian
 * @date 2023/4/16
 **/
public class ReadWriteLockDemoV3_002 {

    public static void main(String[] args) {

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    }

}
