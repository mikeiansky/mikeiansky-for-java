package com.winson.protocol.rpc.custom.v2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author winson
 * @date 2022/5/23
 **/
public class WinsonRpcProtocolProcess {

    private int startPosition;
    private int dataLength;
    private int bodyLength;
    private final ByteBuffer byteBuffer;
    private byte[] tempData;

    private boolean isHeadReady;
    private boolean isBodyReady;

    private WinsonRpcHeader rpcHeader;
    private WinsonRpcBody rpcBody;
    private Object result;

    public WinsonRpcProtocolProcess(int alloc) {
        this.byteBuffer = ByteBuffer.allocate(alloc);
    }

    public void offerResult(Object result) {
        this.result = result;
    }

    public void sendResult(SocketChannel socketChannel) throws IOException {
        String sr = (String) result;
        byteBuffer.clear();
        byteBuffer.put(sr.getBytes(StandardCharsets.UTF_8));
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
    }

    public boolean canReadHeader() {
        return byteBuffer.position() >= WinsonRpcHeader.HEAD_LENGTH;
    }

    public boolean canReadBody() {
        if (!isHeadReady) {
            return false;
        }
        if (isBodyReady) {
            return false;
        }
        int currentLength = byteBuffer.position() - startPosition;
        if (tempData != null) {
            currentLength += tempData.length;
        }
        return currentLength >= bodyLength;
    }

    public boolean isHeadReady() {
        return isHeadReady;
    }

    public boolean isBodyReady() {
        return isBodyReady;
    }

    WinsonRpcBody getRpcBody(){
        return rpcBody;
    }

    public WinsonRpcHeader readHeader() throws IOException, ClassNotFoundException {
        byte[] headBytes = new byte[WinsonRpcHeader.HEAD_LENGTH];
        int tempPosition = byteBuffer.position();
        byteBuffer.position(0);
        byteBuffer.get(headBytes);
        startPosition = byteBuffer.position();
        byteBuffer.position(tempPosition);
        ByteArrayInputStream in = new ByteArrayInputStream(headBytes);
        ObjectInputStream objIn = new ObjectInputStream(in);
        WinsonRpcHeader header = (WinsonRpcHeader) objIn.readObject();
        rpcHeader = header;
        bodyLength = header.getTotalLength();
        isHeadReady = true;
        return header;
    }

    public void readBody() throws IOException, ClassNotFoundException {
        byte[] body = new byte[bodyLength];
        int bodyStartPosition = 0;
        if (tempData != null) {
            System.arraycopy(tempData, 0, body, 0, tempData.length);
            bodyStartPosition = tempData.length;
        }
        int leftLength = bodyLength - bodyStartPosition;
        for (int i = 0; i < leftLength; i++) {
            body[bodyStartPosition + i] = byteBuffer.get(startPosition + i);
        }
        ByteArrayInputStream bodyIn = new ByteArrayInputStream(body);
        ObjectInputStream bodyObjIn = new ObjectInputStream(bodyIn);
        WinsonRpcBody winsonRpcBody = (WinsonRpcBody) bodyObjIn.readObject();
        rpcBody = winsonRpcBody;
        isBodyReady = true;
    }

    public int readData(SocketChannel socketChannel) throws IOException {
        int readLength = socketChannel.read(byteBuffer);
        if (readLength > 0) {
            dataLength += readLength;
        }
        return readLength;
    }

    public void checkData() {
        if (!byteBuffer.hasRemaining()) {
            // 没有空间了
            int length = byteBuffer.limit() - startPosition;
            byteBuffer.position(startPosition);
            if (tempData == null) {
                tempData = new byte[length];
                byteBuffer.get(tempData);
            } else {
                int newLength = tempData.length + length;
                byte[] temp = new byte[newLength];
                System.arraycopy(tempData, 0, temp, 0, tempData.length);
                int tempStart = tempData.length;
                for (int i = 0; i < length; i++) {
                    temp[tempStart + i] = byteBuffer.get(startPosition + i);
                }
                tempData = temp;
            }
            startPosition = 0;
            byteBuffer.clear();
        }
    }

}
