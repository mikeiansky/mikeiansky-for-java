package com.winson.protocol.rpc.custom.v3;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author winson
 * @date 2022/5/23
 **/
public class WinsonRpcEventWorkerGroup {

    private AtomicLong nextId = new AtomicLong();
    private int size;
    private final WinsonRpcWorkThread[] threads;

    public WinsonRpcEventWorkerGroup(int size) {
        this.size = size;
        threads = new WinsonRpcWorkThread[size];
        for (int i = 0; i < size; i++) {
            threads[i] = new WinsonRpcWorkThread("winson-rpc-work-" + i);
            threads[i].start();
        }
    }

    public void work(long pid, Runnable task) {
        int index = (int) (pid % size);
        threads[index].addTask(task);
//        task.run();
    }

}
