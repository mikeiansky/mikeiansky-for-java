package com.winson.framework.netty.v2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author winson
 * @date 2021/8/27
 **/
public class BossThread implements Runnable {

    private Selector selector;
    private LinkedBlockingQueue<ServerSocketChannel> serverSocketChannels = new LinkedBlockingQueue<>();
    private BossThreadGroup btg;

    public BossThread(BossThreadGroup btg) {
        this.btg = btg;
        try {
            this.selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {

            try {
                int size = selector.select();
                if (size > 0) {
                    Set<SelectionKey> keySet = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keySet.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isAcceptable()) {
                            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            System.out.println(Thread.currentThread().getName() + " accept socket channel : " + socketChannel);
                            btg.acceptSocketChannel(socketChannel);
                        }

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            // run task
            if (!serverSocketChannels.isEmpty()) {
                int size = serverSocketChannels.size();
                for (int i = 0; i < size; i++) {
                    try {
                        ServerSocketChannel serverSocketChannel = serverSocketChannels.take();
                        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ClosedChannelException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public void listen(String host, int port) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(host, port));

            serverSocketChannels.add(serverSocketChannel);
            selector.wakeup();
            System.out.println(Thread.currentThread().getName() + " server start ... " );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
