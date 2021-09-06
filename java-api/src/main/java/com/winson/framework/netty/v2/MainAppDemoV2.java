package com.winson.framework.netty.v2;

import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2021/8/27
 **/
public class MainAppDemoV2 {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(1);

        BossThreadGroup btg = new BossThreadGroup(2, 4);
        btg.listen("192.168.159.1", 10001);

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main app demo v2 finish ... ");
    }

}
