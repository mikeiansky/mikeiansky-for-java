package com.winson.jdkapi.io.nio.v2;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author winson
 * @date 2022/6/4
 **/
public class WinsonNioServerDemoV2 {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(20001));
        ServerSocket s1 = serverSocketChannel.socket();
        ServerSocket s2 = serverSocketChannel.socket();
        ServerSocket s3 = serverSocketChannel.socket();
        System.out.println("ss1 : "+s1);
        System.out.println("ss2 : "+s2);
        System.out.println("ss3 : "+s3);
        System.out.println("s1 == s2 : " + (s1 == s2));
        System.out.println("s2 == s3 : " + (s2 == s3));
        System.out.println("s1 == s3 : " + (s1 == s3));

        Selector selector = Selector.open();
        Selector selector2 = Selector.open();
        Selector selector3 = Selector.open();
        System.out.println("selector : " +selector);
        System.out.println("selector2 : " +selector2);
        System.out.println("selector3 : " +selector3);

//        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, new WinsonNioServerDemoV2());
        SelectionKey selectionKey = serverSocketChannel.register(selector, 0, new WinsonNioServerDemoV2());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("selector.wakeup() ... ");
                selector.wakeup();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("ready accept ... ");
                selectionKey.interestOps(SelectionKey.OP_ACCEPT);
                selector.wakeup();
            }
        }).start();
//        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, new WinsonNioServerDemoV2());
//        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("server start ... ");
        while (true) {
            int keySize = selector.select();
            System.out.println("keySize : " + keySize);
            Set<SelectionKey> keySet = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keySet.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();
                if (key.isValid() && key.isAcceptable()) {
                    System.out.println(" accept key .... ");
                    ServerSocketChannel serverSocket = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = serverSocket.accept();
                    socketChannel.configureBlocking(false);

                }

            }
        }

    }

}
