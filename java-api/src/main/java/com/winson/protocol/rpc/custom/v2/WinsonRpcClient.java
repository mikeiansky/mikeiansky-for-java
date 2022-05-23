package com.winson.protocol.rpc.custom.v2;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * @author winson
 * @date 2022/5/23
 **/
public class WinsonRpcClient {

    public static  <T> T createService(Class<T> service) {

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

                ByteArrayOutputStream headOut = new ByteArrayOutputStream();
                ObjectOutputStream headObjOut = new ObjectOutputStream(headOut);
                headObjOut.writeObject(header);
                byte[] headByteArray = headOut.toByteArray();
                System.out.println("head length : " + headByteArray.length);
                // write header

                socket.connect(new InetSocketAddress("localhost", 9001));
                OutputStream out = socket.getOutputStream();
                out.write(headByteArray);
                //        Thread.sleep(1000);
                // write body
                out.write(bodyByteArray);

                CountDownLatch latch = new CountDownLatch(1);
                new Thread(() -> {
                    try {
                        InputStream in = socket.getInputStream();
                        byte[] buf = new byte[1024];
                        int readLength = 0;
                        while ((readLength = in.read(buf)) != -1) {
                            result = new String(buf, 0, readLength);
//                            System.out.println("read result : " + );
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    latch.countDown();
                }).start();
                latch.await();
                out.close();
                socket.close();

                return result;
            }
        });

    }

    public static void main(String[] args) throws IOException, InterruptedException {

        WinsonHelloService winsonHelloService = WinsonRpcClient.createService(WinsonHelloService.class);
        System.out.println("result is : "+winsonHelloService.sayHello("v2 rpc test - 002"));
        System.out.println("client end ... ");

    }

}
