package com.winson.protocol.rpc.custom.v4;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author winson
 * @date 2022/5/29
 **/
public class WinsonWorkGroupLoop {

    private AtomicInteger id = new AtomicInteger();

    private final WinsonWorkGroupLoopRunner[] threads;

    public WinsonWorkGroupLoop(int size) {
        threads = new WinsonWorkGroupLoopRunner[size];

    }

    public void init() {
        int size = threads.length;
        for (int i = 0; i < size; i++) {
            threads[i] = new WinsonWorkGroupLoopRunner("winson-work-group-" + i);
            threads[i].start();
        }
    }

    public void addEvent(SocketChannel socketChannel) throws IOException {
        threads[id.getAndIncrement() % threads.length].addEvent(socketChannel);
    }

}
