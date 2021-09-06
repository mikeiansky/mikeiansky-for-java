package com.winson.framework.netty.v2;

import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author winson
 * @date 2021/8/27
 **/
public class WorkThreadGroup {

    private WorkThread[] workThreads;

    private AtomicInteger xid = new AtomicInteger();

    public WorkThreadGroup(int work) {
        workThreads = new WorkThread[work];
        for (int i = 0; i < work; i++) {
            workThreads[i] = new WorkThread(this);
            new Thread(workThreads[i]).start();
        }
    }

    private WorkThread next() {
        return workThreads[xid.getAndIncrement() % workThreads.length];
    }

    public void acceptSocketChannel(SocketChannel socketChannel) {
        WorkThread workThread = next();
        workThread.acceptSocket(socketChannel);
    }

}
