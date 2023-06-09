package com.winson.jdkapi.juc.poolv2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author mike ian
 * @date 2023/6/8
 * @desc
 **/
public class PoolDemoV2 {

    public static void main(String[] args) {

        ThreadPoolExecutor tpl = new ThreadPoolExecutor(4,
                10,
                600,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1024));

        tpl.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        });

//        tpl.submit(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("hello world 2");
//            }
//        });

        System.out.println("main complete");
    }

}
