package com.winson.framework.netty.v2;

import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author winson
 * @date 2021/8/27
 **/
public class BossThreadGroup {

    private BossThread[] bossThreads;
    private WorkThreadGroup workThreadGroup;
    private AtomicInteger xid = new AtomicInteger();

    public BossThreadGroup(int size, int work) {
        bossThreads = new BossThread[size];

        for (int i = 0; i < size; i++) {
            bossThreads[i] = new BossThread(this);
            new Thread(bossThreads[i]).start();
        }

        workThreadGroup = new WorkThreadGroup(work);

    }

    public void listen(String host, int port){
        BossThread bossThread = next();
        bossThread.listen(host, port);
    }

    public void acceptSocketChannel(SocketChannel socketChannel){
        workThreadGroup.acceptSocketChannel(socketChannel);
    }

    private BossThread next(){
        return bossThreads[xid.incrementAndGet() % bossThreads.length];
    }

}
