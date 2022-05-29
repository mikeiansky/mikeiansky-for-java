package com.winson.protocol.rpc.custom.v4;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author winson
 * @date 2022/5/29
 **/
public class WinsonWorkGroupLoopRunner extends Thread {

    private Selector selector;

    private boolean cancel;

    private final Object lock = new Object();

    public WinsonWorkGroupLoopRunner(String name) {
        super(name);
        try {
            selector = Selector.open();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addEvent(SocketChannel socketChannel) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ, buf);
        selector.wakeup();
    }

    @Override
    public void run() {
        while (!cancel) {
            try {
                selector.select();
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey selectionKey = keyIterator.next();
                    keyIterator.remove();
                    if (selectionKey.isValid() && selectionKey.isReadable()) {
                        readData(selectionKey);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void readData(SelectionKey selectionKey) throws IOException {
        // read data
        ByteBuffer buf = (ByteBuffer) selectionKey.attachment();
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        int position = buf.position();
        int totalLength = 0;
        int readLength = socketChannel.read(buf);
        totalLength += readLength;
        while (true) {
            if (readLength > 0) {
                System.out.println("[" + Thread.currentThread().getName() + "] read data length : "
                        + readLength + " cp : " + position + " , totalLength : " + totalLength);
                if (!buf.hasRemaining()) {
                    // 需要清除数据否则读取数据为空
                    readLength = socketChannel.read(buf);
                    System.out.println("[" + Thread.currentThread().getName() + "] buf has no space for data and read length : "+readLength );
                    buf.clear();
                    totalLength = 0;
                    position = 0;
                }
                readLength = socketChannel.read(buf);
                totalLength += readLength;
                byte[] tb = new byte[totalLength];
                for (int i = 0; i < totalLength; i++) {
                    tb[i] = buf.get(position + i);
                }
//                String msg = new String(tb, "utf-8");
//                System.out.println("[" + Thread.currentThread().getName() + "]" + " read msg position : " + position
//                        + " , readLength : " + readLength + " , msg : " + msg + " , after read position : " + buf.position());
            } else if (readLength == 0) {
                System.out.println("[" + Thread.currentThread().getName() + "] readLength == 0 and break");
                break;
            } else {
                // close
                System.out.println("[" + Thread.currentThread().getName() + "] socket channel is closed ... and break");
                selectionKey.cancel();
                socketChannel.close();
                break;
            }
        }
    }

}
