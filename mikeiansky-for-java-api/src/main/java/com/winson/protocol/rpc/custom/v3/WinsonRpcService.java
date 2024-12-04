package com.winson.protocol.rpc.custom.v3;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author winson
 * @date 2022/5/23
 **/
public class WinsonRpcService {

    private boolean stop;
    private AtomicLong processId = new AtomicLong();
    private WinsonRpcServiceManager winsonRpcServiceManager;
    private WinsonRpcEventBossGroup bossGroup;
    private WinsonRpcEventWorkerGroup workerGroup;
    private AtomicLong taskId = new AtomicLong();

    public WinsonRpcService(WinsonRpcEventBossGroup bossGroup, WinsonRpcEventWorkerGroup workerGroup) {
        winsonRpcServiceManager = new WinsonRpcServiceManager();
        this.bossGroup = bossGroup;
        this.workerGroup = workerGroup;
    }

    public void init() {
        bossGroup.initService(this);
    }

    void start() {
//        CountDownLatch latch = new CountDownLatch(1);
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

//            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void handleAcceptKeyEvent(Selector selector, SelectionKey selectionKey) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
        SocketChannel clientSocketChannel = serverSocketChannel.accept();
        System.out.println("handleAccept accept new client : " + clientSocketChannel);
        WinsonRpcProtocolProcess rpcProtocolHolder = new WinsonRpcProtocolProcess(processId.getAndIncrement(), 1024,
                selector, clientSocketChannel);
        clientSocketChannel.configureBlocking(false);
        clientSocketChannel.register(selector, SelectionKey.OP_READ, rpcProtocolHolder);
    }

    private void handleWriteKeyEvent(Selector selector, SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        WinsonRpcProtocolProcess rpcProtocolProcess = (WinsonRpcProtocolProcess) selectionKey.attachment();
//        workerGroup.work(rpcProtocolProcess);
//        rpcProtocolProcess.sendResult(socketChannel);
        System.out.println(Thread.currentThread().getName() + " , close ---> socketChannel : " + socketChannel);
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
        rpcProtocolProcess.checkData();
        if (rpcProtocolProcess.isHeadReady()) {
            if (rpcProtocolProcess.canReadBody()) {
                // 准备读取数据部分
                rpcProtocolProcess.readBody();
//                Object result = winsonRpcServiceManager.doService(rpcProtocolProcess.getRpcBody());
//                rpcProtocolProcess.offerResult(result);
//                socketChannel.register(selector, selectionKey.interestOps() | SelectionKey.OP_WRITE, rpcProtocolProcess);
                workerGroup.work(taskId.getAndIncrement(), getRequestTask(rpcProtocolProcess, selectionKey));
//                socketChannel.register(selector, selectionKey.interestOps() | SelectionKey.OP_WRITE, rpcProtocolProcess);
            }
        } else {
            if (rpcProtocolProcess.canReadHeader()) {
                // 头部准备好了,读取头部
                rpcProtocolProcess.readHeader();
            }
        }
        return readLength;
    }

    public Runnable getRequestTask(WinsonRpcProtocolProcess rpcProtocolProcess, SelectionKey selectionKey) {
        return () -> {
            try {
                Object result = winsonRpcServiceManager.doService(rpcProtocolProcess.getRpcBody());
                System.out.println("run task on Thread:" + Thread.currentThread().getName() + " , result : " + result);
                rpcProtocolProcess.offerResult(result, selectionKey);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException |
                     ClosedChannelException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//            System.out.println("getRequestTask run complete ");
        };
    }


}
