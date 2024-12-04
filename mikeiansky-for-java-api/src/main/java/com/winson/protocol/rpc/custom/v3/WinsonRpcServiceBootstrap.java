package com.winson.protocol.rpc.custom.v3;

import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2022/5/23
 **/
public class WinsonRpcServiceBootstrap {

    private WinsonRpcEventBossGroup bossGroup;
    private WinsonRpcEventWorkerGroup workerGroup;
    private CountDownLatch latch = new CountDownLatch(1);
    private WinsonRpcService winsonRpcService;

    public WinsonRpcServiceBootstrap(WinsonRpcEventBossGroup bossGroup,
                                     WinsonRpcEventWorkerGroup workerGroup) {
        winsonRpcService = new WinsonRpcService(bossGroup, workerGroup);
        this.bossGroup = bossGroup;
        this.workerGroup = workerGroup;
    }

    public synchronized WinsonRpcServiceBootstrap init() {
        winsonRpcService.init();
        return this;
    }

    public WinsonRpcServiceBootstrap cancel() {
        bossGroup.showDown();
        latch.countDown();
        return this;
    }

    public WinsonRpcServiceBootstrap sync() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

}
