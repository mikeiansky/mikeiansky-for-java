package com.winson.protocol.rpc.custom.v3;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author winson
 * @date 2022/5/23
 **/
public class WinsonRpcWorkThread extends Thread {

    private final Set<Runnable> tasks = new HashSet<>();
    private boolean stop;
    private final Object lock = new Object();

    public WinsonRpcWorkThread(String name) {
        super(name);
    }

    public void addTask(Runnable task) {
        tasks.add(task);
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public void cancel() {
        stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            if (tasks.size() > 0) {
                Iterator<Runnable> trs = tasks.iterator();
                while (trs.hasNext()) {
                    Runnable runnable = trs.next();
                    runnable.run();
                    trs.remove();
                }
            } else {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

}
