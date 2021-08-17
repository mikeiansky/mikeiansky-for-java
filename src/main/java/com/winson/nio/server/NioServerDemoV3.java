package com.winson.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author winson
 * @date 2021/8/17
 **/
public class NioServerDemoV3 {

    public static void main(String[] args) {

        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(10003));
            serverSocketChannel.configureBlocking(false);
            CopyOnWriteArrayList<SocketChannel> channelList = new CopyOnWriteArrayList<>();
            boolean hasInit = false;
            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    System.out.println("server v3 accept socket : " + socketChannel);
                    channelList.add(socketChannel);
                    if(hasInit){
                        continue;
                    }

                    socketChannel.isConnected();
                    new Thread(() -> {
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int readLength = 0;
                        while (true){
//                            System.out.println("channel list size : " + channelList.size());
                            Iterator<SocketChannel> iterator = channelList.iterator();
                            while (iterator.hasNext()){
                                SocketChannel socket = iterator.next();
                                if(socket.isConnected()){
                                    try {
                                        readLength = socketChannel.read(buffer);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    if (readLength > 0) {
                                        System.out.println("client size : " + channelList.size());
                                        System.out.println("read byte length : " + readLength);
                                        buffer.flip();
                                        byte[] buf = new byte[readLength];
                                        buffer.get(buf, 0, readLength);
                                        String result = new String(buf);
                                        System.out.println("read msg result : " + result);
                                    }
                                } else {
                                    System.out.println("socket disconnect : " + socket);
                                    iterator.remove();
                                }
                            }
                        }
                    }).start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(" server v3 finish ... ");

    }

}
