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

    private WinsonRpcServiceManager winsonRpcServiceManager;

    public WinsonRpcService() {
        winsonRpcServiceManager = new WinsonRpcServiceManager();
    }

    public void start() {
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
        WinsonRpcProtocolProcess rpcProtocolHolder = new WinsonRpcProtocolProcess(1024);
        clientSocketChannel.configureBlocking(false);
        clientSocketChannel.register(selector, SelectionKey.OP_READ, rpcProtocolHolder);
    }

    private void handleWriteKeyEvent(Selector selector, SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        WinsonRpcProtocolProcess rpcProtocolHolder = (WinsonRpcProtocolProcess) selectionKey.attachment();
        rpcProtocolHolder.sendResult(socketChannel);
        selectionKey.cancel();
        socketChannel.close();
    }

    private void handleReadKeyEvent(Selector selector, SelectionKey selectionKey) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        WinsonRpcProtocolProcess rpcProtocolProcess = (WinsonRpcProtocolProcess) selectionKey.attachment();

        int length = readData(selector, selectionKey, socketChannel, rpcProtocolProcess);
        for (; ; ) {
            if (length > 0) {
                // read data
                length = readData(selector, selectionKey, socketChannel, rpcProtocolProcess);
            } else if (length == 0) {
                break;
            } else {
                // close
                selectionKey.cancel();
                socketChannel.close();
                break;
            }
        }
    }

    private int readData(Selector selector, SelectionKey selectionKey, SocketChannel socketChannel,
                         WinsonRpcProtocolProcess rpcProtocolProcess) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        int readLength = rpcProtocolProcess.readData(socketChannel);
        handleData(selector, selectionKey, socketChannel, rpcProtocolProcess);
        return readLength;
    }

    private void handleData(Selector selector, SelectionKey selectionKey, SocketChannel socketChannel,
                            WinsonRpcProtocolProcess rpcProtocolProcess) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        rpcProtocolProcess.checkData();
        if (rpcProtocolProcess.isHeadReady()) {
            if (rpcProtocolProcess.canReadBody()) {
                // 准备读取数据部分
                rpcProtocolProcess.readBody();
                Object result = winsonRpcServiceManager.doService(rpcProtocolProcess.getRpcBody());
                rpcProtocolProcess.offerResult(result);
                socketChannel.register(selector, selectionKey.interestOps() | SelectionKey.OP_WRITE, rpcProtocolProcess);
            }
        } else {
            if (rpcProtocolProcess.canReadHeader()) {
                // 头部准备好了,读取头部
                rpcProtocolProcess.readHeader();
            }
        }
    }


    public static void main(String[] args) {
        WinsonRpcService winsonRpcService = new WinsonRpcService();
        winsonRpcService.start();
    }

}
