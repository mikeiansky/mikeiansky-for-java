package com.winson.jdkapi.juc.version_2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author winson
 * @date 2021/5/11
 **/
public class TestThreadLocalOOM {

    // -Xms12m -Xmx12m -Xmn12m
    public static void main(String[] args) {
        System.out.println("test thread local oom start ... ");

        ExecutorService es = Executors.newFixedThreadPool(10);
        boolean flag = true;
        final AtomicLong count = new AtomicLong();
        while (flag){
            try {
                es.submit(() -> {
                    try {
                        ThreadLocal<String> tl = new ThreadLocal<>();
                        tl.set("winson"+count.get());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        System.out.println("test thread local oom end ... ");
    }

}
