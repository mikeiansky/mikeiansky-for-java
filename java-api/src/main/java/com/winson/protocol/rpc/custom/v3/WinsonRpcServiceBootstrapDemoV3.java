package com.winson.protocol.rpc.custom.v3;

/**
 * @author winson
 * @date 2022/5/23
 **/
public class WinsonRpcServiceBootstrapDemoV3 {

    public static void main(String[] args) {

        WinsonRpcEventBossGroup bossGroup = new WinsonRpcEventBossGroup(1);
        WinsonRpcEventWorkerGroup workerGroup = new WinsonRpcEventWorkerGroup(5);
        WinsonRpcServiceBootstrap bootstrap = new WinsonRpcServiceBootstrap(bossGroup, workerGroup);
        bootstrap.init()
                .sync();

        bootstrap.cancel();

    }

}
