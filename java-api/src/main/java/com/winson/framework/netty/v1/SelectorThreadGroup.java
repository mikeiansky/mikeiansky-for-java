package com.winson.framework.netty.v1;

import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author winson
 * @date 2021/8/27
 **/
public class SelectorThreadGroup {

    private SelectorThread[] selectorThreads;

    private AtomicInteger xid = new AtomicInteger();

    public SelectorThreadGroup(int size) {
        selectorThreads = new SelectorThread[size];
        for (int i = 0; i < size; i++) {
            selectorThreads[i] = new SelectorThread(this);
            new Thread(selectorThreads[i]).start();
        }
    }

    public SelectorThread next() {
        return selectorThreads[xid.incrementAndGet() % selectorThreads.length];
    }

    public void listen(String host, int port) {
        SelectorThread selectorThread = next();
        selectorThread.listen(host, port);
    }

    public void handleChannel(SocketChannel channel){
        SelectorThread selectorThread = next();
        selectorThread.handleChannel(channel);
    }

}
