package com.winson.protocol.rpc.custom.v2;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2022/5/23
 **/
public class WinsonRpcService {

    private boolean stop;

    public void start() {
        System.out.println("winson rpc service start ... ");
        CountDownLatch latch = new CountDownLatch(1);
        try {
            Selector selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(9001));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (!stop) {
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if (!selectionKey.isValid()) {
                        continue;
                    }
                    if (selectionKey.isAcceptable()) {
                        handleAcceptKeyEvent(selector, selectionKey);
                    } else if (selectionKey.isReadable()) {
                        handleReadKeyEvent(selector, selectionKey);
                    } else if (selectionKey.isWritable()) {
                        handleWriteKeyEvent(selector, selectionKey);
                    }
                }

            }

            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void handleAcceptKeyEvent(Selector selector, SelectionKey selectionKey) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
        SocketChannel clientSocketChannel = serverSocketChannel.accept();
        System.out.println("handleAccept accept new client : " + clientSocketChannel);
        WinsonRpcProtocolHolder rpcProtocolHolder = new WinsonRpcProtocolHolder(1024);
        clientSocketChannel.configureBlocking(false);
        clientSocketChannel.register(selector, SelectionKey.OP_READ, rpcProtocolHolder);
    }

    private void handleWriteKeyEvent(Selector selector, SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        WinsonRpcProtocolHolder rpcProtocolHolder = (WinsonRpcProtocolHolder) selectionKey.attachment();
        rpcProtocolHolder.sendResult(socketChannel);
        selectionKey.cancel();
        socketChannel.close();
    }

    private void handleReadKeyEvent(Selector selector, SelectionKey selectionKey) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        WinsonRpcProtocolHolder rpcProtocolHolder = (WinsonRpcProtocolHolder) selectionKey.attachment();

        int length = readData(selector, selectionKey, socketChannel, rpcProtocolHolder);
        for (; ; ) {
            if (length > 0) {
//                System.out.println("handleReadKeyEvent length > 0");
                // read data
                length = readData(selector, selectionKey, socketChannel, rpcProtocolHolder);
            } else if (length == 0) {
                // break;
//                System.out.println("handleReadKeyEvent length == 0");
                break;
            } else {
//                System.out.println("handleReadKeyEvent length < 0");
                // close
                selectionKey.cancel();
                socketChannel.close();
                break;
            }
        }
    }

    private int readData(Selector selector, SelectionKey selectionKey, SocketChannel socketChannel, WinsonRpcProtocolHolder rpcProtocolHolder) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        int readLength = rpcProtocolHolder.readData(socketChannel);
        handleData(selector, selectionKey, socketChannel, rpcProtocolHolder);
        return readLength;
    }

    private void handleData(Selector selector, SelectionKey selectionKey, SocketChannel socketChannel, WinsonRpcProtocolHolder rpcProtocolHolder) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        rpcProtocolHolder.checkData();
        if (rpcProtocolHolder.isHeadReady()) {
            if (!rpcProtocolHolder.isBodyReady() && rpcProtocolHolder.canReadBody()) {
                // 准备读取数据部分
                rpcProtocolHolder.readBody();
                Object result = rpcProtocolHolder.process();
                rpcProtocolHolder.offerResult(result);
                socketChannel.register(selector, selectionKey.interestOps() | SelectionKey.OP_WRITE, rpcProtocolHolder);
            }
        } else {
            if (rpcProtocolHolder.canReadHeader()) {
                // 头部准备好了,读取头部
                WinsonRpcHeader header = rpcProtocolHolder.readHeader();
                System.out.println("handle data header is : " + header);
            }
        }
    }


    public static void main(String[] args) {
        WinsonRpcService winsonRpcService = new WinsonRpcService();
        winsonRpcService.start();
    }

}
