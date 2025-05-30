package com.winson.netty.v2.jdk;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;

/**
 * @author mike ian
 * @date 2025/5/30
 * @desc
 **/
public class JdkNioSocketDemoV2 {

    public static void main(String[] args) throws IOException {

        SelectorProvider selectorProvider = SelectorProvider.provider();
        Selector selector = selectorProvider.openSelector();
        System.out.println(selector.selectNow());
        System.out.println(selector.selectNow());
        System.out.println(selector.selectNow());

    }

}
