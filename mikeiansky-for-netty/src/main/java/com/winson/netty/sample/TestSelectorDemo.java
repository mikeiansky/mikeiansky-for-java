package com.winson.netty.sample;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * @author mike ian
 * @date 2024/12/24
 * @desc
 **/
public class TestSelectorDemo {

    public static void main(String[] args) throws IOException {
        System.out.println("app start ... ");
        Selector selector = Selector.open();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                selector.wakeup();
            }
        }).start();

        int selectKey = selector.select();
        System.out.println(selectKey);

        System.out.println("app complete ... ");

    }

}
