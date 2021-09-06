package com.winson.framework.netty.v1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author winson
 * @date 2021/8/27
 **/
public class SelectorThread implements Runnable {

    private final Selector selector;
    private final SelectorThreadGroup stg;

    private final LinkedBlockingQueue<Channel> channelQueue = new LinkedBlockingQueue<>();

    public SelectorThread(SelectorThreadGroup stg) {
        this.stg = stg;
        Selector temp = null;
        try {
            temp = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
        selector = temp;
    }

    public void putChannel(Channel channel) {

    }

    public void listen(String host, int port) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(host, port));

            channelQueue.put(serverSocketChannel);
            selector.wakeup();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void handleChannel(Channel channel) {
        try {
            channelQueue.put(channel);
            selector.wakeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        while (true) {
            try {
//                System.out.println(Thread.currentThread().getName() + " before select key size : " + selector.selectedKeys().size());
                int num = selector.select();
//                System.out.println(Thread.currentThread().getName() + " after select key size : " + selector.selectedKeys().size());
                if (num > 0) {
                    Set<SelectionKey> keySet = selector.selectedKeys();
                    Iterator<SelectionKey> keyIterator = keySet.iterator();
                    while (keyIterator.hasNext()) {
                        SelectionKey key = keyIterator.next();
                        keyIterator.remove();
                        if (key.isAcceptable()) {
                            acceptHandle(key);
                        } else if (key.isReadable()) {
//                            System.out.println(Thread.currentThread().getName() + " read byte ");
                            readHandle(key);
                        } else if (key.isWritable()) {
                            writeHandle(key);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // run task
            runCommonTask();
        }

    }

    private void runCommonTask() {
        int size = channelQueue.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                try {
                    handleChannelRegister(channelQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void handleChannelRegister(Channel channel) {
        try {
            if (channel instanceof SocketChannel) {
                ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
                ((SocketChannel) channel).register(selector, SelectionKey.OP_READ, buffer);
            } else {
                ((ServerSocketChannel) channel).register(selector, SelectionKey.OP_ACCEPT);
            }
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
    }

    private void acceptHandle(SelectionKey key) {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        try {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            System.out.println(Thread.currentThread().getName() + " accept socket : " + socketChannel);

//            channelQueue.put(socketChannel);
//            selector.wakeup();

//            System.in.read();

            stg.handleChannel(socketChannel);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readHandle(SelectionKey key) {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
//        key.cancel();
        while (true) {
            try {
                buffer.clear();
                int readLength = socketChannel.read(buffer);
                if (readLength > 0) {
                    buffer.flip();

//                    while (buffer.hasRemaining()){
//                        socketChannel.write(buffer);
//                    }

                    byte[] buf = new byte[readLength];
                    buffer.get(buf);
                    String readMessage = new String(buf);
                    System.out.println("read message is : " + readMessage);

                    buffer.flip();

                    socketChannel.register(selector, SelectionKey.OP_WRITE, buffer);

                } else if (readLength == 0) {
                    break;
                } else {
                    System.out.println(Thread.currentThread().getName() + " channel close : " + socketChannel);
                    key.cancel();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void writeHandle(SelectionKey key) {
        SocketChannel socketChannel = (SocketChannel) key.channel();
//        System.out.println(Thread.currentThread() + " , write handle : " + key.attachment());
        try {

            ByteBuffer buffer = (ByteBuffer) key.attachment();
            System.out.println(Thread.currentThread().getName() + " , echo message");
            try {
                while (buffer.hasRemaining()) {
                    socketChannel.write(buffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            buffer.clear();
            socketChannel.register(selector, SelectionKey.OP_READ, buffer);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
    }

}
