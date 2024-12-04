package com.winson.protocol.rpc.custom.v3;

import org.openqa.selenium.By;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author winson
 * @date 2022/5/23
 **/
public class WinsonRpcClient {

    public static final AtomicLong id = new AtomicLong();

    public static <T> T createService(Class<T> service) {

        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{service}, new InvocationHandler() {

            public Object result;

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                Socket socket = new Socket();
                ByteArrayOutputStream bodyOut = new ByteArrayOutputStream();
                WinsonRpcBody rpcBody = new WinsonRpcBody();
                rpcBody.setServiceName(service.getName());
                rpcBody.setMethodName(method.getName());
                rpcBody.setParamTypes(method.getParameterTypes());
//                rpcBody.setClazz(service.getName());
                rpcBody.setArgs(args);
                ObjectOutputStream bodyObjOut = new ObjectOutputStream(bodyOut);
                bodyObjOut.writeObject(rpcBody);
                byte[] bodyByteArray = bodyOut.toByteArray();

                WinsonRpcHeader header = new WinsonRpcHeader();
                header.setTotalLength(bodyByteArray.length);
                header.setId(id.incrementAndGet());

                ByteArrayOutputStream headOut = new ByteArrayOutputStream();
                ObjectOutputStream headObjOut = new ObjectOutputStream(headOut);
                headObjOut.writeObject(header);
                byte[] headByteArray = headOut.toByteArray();

                socket.connect(new InetSocketAddress("localhost", 9001));
                OutputStream out = socket.getOutputStream();
                // write header
                out.write(headByteArray);
                // write body
                out.write(bodyByteArray);

                InputStream in = socket.getInputStream();
                int readLength = 0;
                int totalLength = 0;
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                byte[] buf = new byte[1024];
                while ((readLength = in.read(buf)) != -1) {
                    totalLength += readLength;
//                    int start = totalLength;
//                    int cl = totalLength + readLength;
//                    if(temp == null){
//                        temp = new byte[cl];
//                        System.arraycopy(buf, 0, temp, 0 , readLength);
//                    } else {
//                        System.arraycopy(buf, 0, temp, start , readLength);
//
//                    }
//                    System.out.println("read length : " + readLength);
                    byteBuffer.put(buf, 0, readLength);

                    if (totalLength >= WinsonRpcResponseHeader.RESPONSE_HEAD_LENGTH) {
                        byteBuffer.flip();
                        byte[] headBuf = new byte[WinsonRpcResponseHeader.RESPONSE_HEAD_LENGTH];
                        byteBuffer.get(headBuf);
                        ByteArrayInputStream headBin = new ByteArrayInputStream(headBuf);
                        ObjectInputStream objHeadReader = new ObjectInputStream(headBin);
                        WinsonRpcResponseHeader responseHead = (WinsonRpcResponseHeader) objHeadReader.readObject();

                        byte[] bodyBuf = new byte[responseHead.getDataLength()];
                        byteBuffer.get(bodyBuf);
                        ByteArrayInputStream bodyBin = new ByteArrayInputStream(bodyBuf);
                        ObjectInputStream objBodyReader = new ObjectInputStream(bodyBin);
                        Object result = objBodyReader.readObject();

                        System.out.println("result ------> " + result);
                    }
//                    byteBuffer.put(buf, 0, readLength);
                }

//                byteBuffer.flip();
//                byte[] headBuf = new byte[WinsonRpcResponseHeader.RESPONSE_HEAD_LENGTH];
//                byteBuffer.get(headBuf);
//                ByteArrayInputStream headBin = new ByteArrayInputStream(headBuf);
//                ObjectInputStream objHeadReader = new ObjectInputStream(headBin);
//                WinsonRpcResponseHeader responseHead = (WinsonRpcResponseHeader) objHeadReader.readObject();
//
//                byte[] bodyBuf = new byte[responseHead.getDataLength()];
//                byteBuffer.get(bodyBuf);
//                ByteArrayInputStream bodyBin = new ByteArrayInputStream(bodyBuf);
//                ObjectInputStream objBodyReader = new ObjectInputStream(bodyBin);
//                Object result = objBodyReader.readObject();
//
//                System.out.println("result ------> " + result);

                out.close();
                socket.close();

                return result;
            }
        });

    }

    public static void main(String[] args) throws IOException, InterruptedException {

        WinsonHelloService winsonHelloService = WinsonRpcClient.createService(WinsonHelloService.class);
        System.out.println("result is : " + winsonHelloService.sayHello("v2 rpc test - 004"));
        System.out.println("client end ... ");

    }

}
