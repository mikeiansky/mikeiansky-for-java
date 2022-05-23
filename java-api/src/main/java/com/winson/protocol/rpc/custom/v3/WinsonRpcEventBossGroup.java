package com.winson.protocol.rpc.custom.v3;

/**
 * @author winson
 * @date 2022/5/23
 **/
public class WinsonRpcEventBossGroup {

    private final Thread[] threads;
    private boolean stop;
    private volatile int index;
    private int size;

    public WinsonRpcEventBossGroup(int size) {
        this.size = size;
        this.threads = new Thread[size];
    }

    public void showDown() {
        stop = true;
    }

    public synchronized void initService(WinsonRpcService winsonRpcService) {
        if (index > size) {
            return;
        }
        threads[index] = new Thread(winsonRpcService::start, "winson-rpc-boss-group-" + index);
        threads[index].start();
//        winsonRpcService.start();
    }

}
