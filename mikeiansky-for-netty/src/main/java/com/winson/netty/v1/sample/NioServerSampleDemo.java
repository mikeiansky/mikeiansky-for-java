package com.winson.netty.v1.sample;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author mike ian
 * @date 2024/12/24
 * @desc
 **/
public class NioServerSampleDemo {

    public static void log(String msg) {
        System.out.println(Thread.currentThread().getName() + " ==> " + msg);

    }

    public static class MyEventLoop {

        private List<Runnable> tasks = new ArrayList<Runnable>();

        private Selector selector = Selector.open();

        public MyEventLoop() throws IOException {
            selector = Selector.open();
        }

        public void init(String name) {
            new Thread(() -> {
                for (; ; ) {
                    try {
                        int selectKey = selector.select();
                        log("loop select key int is " + selectKey);
                        Set<SelectionKey> keySet = selector.selectedKeys();
                        Iterator<SelectionKey> iterator = keySet.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey selectionKey = iterator.next();
                            log("loop selectionKey is " + selectionKey);
                            iterator.remove();

                            if (selectionKey.isReadable()) {
                                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                                ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                                int length = socketChannel.read(buffer);
                                log("loop data is : ***'" + new String(buffer.array(), 0, buffer.position()) + "'*** , length = " + length);
                            }
                        }
                        if (selectKey > 0) {

                        } else {
                            log("task select key is " + selectKey);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // run task
                    tasks.stream().forEach(Runnable::run);
                    tasks.clear();
                }
            }, name).start();
        }

        public void execute(Runnable runnable) {
            tasks.add(runnable);
            selector.wakeup();
        }

        public void register(SocketChannel socketChannel) throws ClosedChannelException {
//            execute(() -> {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                try {
                    socketChannel.register(selector, SelectionKey.OP_READ, buffer);
                    selector.wakeup();
                } catch (ClosedChannelException e) {
                    throw new RuntimeException(e);
                }
//            });

        }

    }

    public static void main(String[] args) throws IOException {
        int index = 0;
        int loopSize = 2;
        MyEventLoop[] myEventLoops = new MyEventLoop[loopSize];
        for (int i = 0; i < loopSize; i++) {
            myEventLoops[i] = new MyEventLoop();
            myEventLoops[i].init("server-" + i);
        }

        // ready loop
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(9090));
        Selector selector = Selector.open();
//        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        log("server is ready");
        boolean start = true;
        while (start) {
            int selectKey = selector.select();
            log("select key int is " + selectKey);
            Set<SelectionKey> keySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                log("selectionKey is " + selectionKey);
                iterator.remove();

                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = server.accept();
                    log("socketChannel is " + socketChannel);
                    socketChannel.configureBlocking(false);
                    myEventLoops[index++ % loopSize].register(socketChannel);
                }
            }
        }

        log("server is complete ... ");

    }

}
