package com.winson.protocol.rpc.custom.v4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author winson
 * @date 2022/5/29
 **/
public class WinsonRpcServer {

    private Selector selector;

    private WinsonWorkGroupLoop workGroupLoop;

    public WinsonRpcServer(WinsonWorkGroupLoop workGroupLoop) {
        this.workGroupLoop = workGroupLoop;
    }

    public void init() throws IOException {
        workGroupLoop.init();
        this.selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(10001));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void start() throws IOException {
        while (true) {
            selector.select();
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();
                keyIterator.remove();
                if (selectionKey.isValid() && selectionKey.isAcceptable()) {
                    // accept
                    ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = ssc.accept();
                    workGroupLoop.addEvent(socketChannel);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        WinsonWorkGroupLoop winsonWorkGroupLoop = new WinsonWorkGroupLoop(5);
        WinsonRpcServer rpcServer = new WinsonRpcServer(winsonWorkGroupLoop);
        rpcServer.init();
        rpcServer.start();
    }

}
