package com.winson.framework.netty.v2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author winson
 * @date 2021/8/27
 **/
public class WorkThread implements Runnable {

    private WorkThreadGroup wtg;

    private Selector selector;

    private LinkedBlockingQueue<SocketChannel> socketChannels = new LinkedBlockingQueue<>();

    public WorkThread(WorkThreadGroup wtg) {
        this.wtg = wtg;
        try {
            selector = Selector.open();
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
                        SocketChannel socketChannel = (SocketChannel) key.channel();

                        if (key.isReadable()) {
                            ByteBuffer buffer = (ByteBuffer) key.attachment();
                            while (true) {
                                int readLength = socketChannel.read(buffer);
                                System.out.println(Thread.currentThread().getName() + " read data length : " + readLength);
                                if (readLength > 0) {
                                    buffer.flip();
                                    while (buffer.hasRemaining()){
                                        socketChannel.write(buffer);
                                    }
                                    buffer.clear();
                                } else if (readLength == 0) {
                                    break;
                                } else {
                                    System.out.println(Thread.currentThread().getName() + " channel closed , channel is : " + socketChannel);
                                    key.cancel();
                                    break;
                                }
                            }
                        }

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // run task

            if (!socketChannels.isEmpty()) {
                int size = socketChannels.size();
                for (int i = 0; i < size; i++) {
                    try {
                        SocketChannel socketChannel = socketChannels.take();
                        socketChannel.configureBlocking(false);
                        ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
                        socketChannel.register(selector, SelectionKey.OP_READ, buffer);
                        System.out.println(Thread.currentThread().getName() + " register socket channel : " + socketChannel);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public void acceptSocket(SocketChannel socketChannel) {
        socketChannels.add(socketChannel);
        selector.wakeup();
    }

}
