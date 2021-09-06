package com.winson.framework.netty.rpc_v1;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author winson
 * @date 2021/8/28
 **/
public class NettyRpcTestV1 {

    public interface Car {
        void race(String flag);
    }

    public static class MyHeader implements Serializable {
        private int flag;
        private long requestId;
        private int length;

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public long getRequestId() {
            return requestId;
        }

        public void setRequestId(long requestId) {
            this.requestId = requestId;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        @Override
        public String toString() {
            return "MyHeader{" +
                    "flag=" + flag +
                    ", requestId=" + requestId +
                    ", length=" + length +
                    '}';
        }
    }

    public static class MyContent implements Serializable {
        private String name;
        private String method;
        private Object[] args;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public Object[] getArgs() {
            return args;
        }

        public void setArgs(Object[] args) {
            this.args = args;
        }

        @Override
        public String toString() {
            return "MyContent{" +
                    "name='" + name + '\'' +
                    ", method='" + method + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

    public static <T> T getRpc(Class<T> clazz, T t) {

        Class[] interfaces = new Class[]{clazz};

        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(" invoke get rpc invoke ");
                method.invoke(t, args);
                System.out.println(" get rpc 2");

                // 组装数据
                MyContent content = new MyContent();
                content.setName(clazz.getName());
                content.setMethod(method.getName());
                content.setArgs(args);

                ByteArrayOutputStream bo = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(bo);
                out.writeObject(content);
                byte[] contentByte = bo.toByteArray();

                MyHeader myHeader = new MyHeader();
                myHeader.setFlag(0x14141414);
                myHeader.setRequestId(UUID.randomUUID().getLeastSignificantBits());
                myHeader.setLength(contentByte.length);

                ByteArrayOutputStream hbo = new ByteArrayOutputStream();
                ObjectOutputStream hout = new ObjectOutputStream(hbo);
                hout.writeObject(myHeader);
                byte[] headByte = hbo.toByteArray();
                System.out.println("head - >" + headByte.length);

                // 1、获取rpc接口

                // 2、调用rpc接口
                sendRpc(headByte, contentByte);

                // 3、等待rpc接口返回

                // 4、返回结果

                return null;
            }
        });

    }


    public static class ClientFactory {

        public ConcurrentHashMap<InetSocketAddress, ClientPool> clientPoolMap = new ConcurrentHashMap<>();

        private static final ClientFactory factory = new ClientFactory();

        private ClientFactory() {

        }

        public static ClientFactory getFactory() {
            return factory;
        }

        public NioSocketChannel getClient(InetSocketAddress address) {

            ClientPool clientPool = clientPoolMap.get(address);
            if (clientPool == null) {
                clientPool = new ClientPool(1, address);
                clientPoolMap.put(address, clientPool);
            }

            return clientPool.getClient();
        }

    }

    public static class ClientPool {
        private int size;

        private NioSocketChannel[] clients;
        private Object[] synObj;
        private AtomicInteger id = new AtomicInteger();
        private InetSocketAddress address;

        public ClientPool(int size, InetSocketAddress address) {
            this.address = address;
            this.size = size;
            clients = new NioSocketChannel[size];
            synObj = new Object[size];
            for (int i = 0; i < size; i++) {
                synObj[i] = new Object();
            }
        }

        public NioSocketChannel getClient() {
            int index = id.incrementAndGet() % clients.length;

            if (clients[index] == null) {
                synchronized (synObj[index]) {
                    if (clients[index] == null) {
                        clients[index] = createClient();
                    }
                }
            }
            return clients[index];
        }

        private NioSocketChannel createClient() {

            NioEventLoopGroup work = new NioEventLoopGroup(2);
            try {
                Channel client = new Bootstrap()
                        .channel(NioSocketChannel.class)
                        .group(work)
                        .handler(new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {

                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        super.channelRead(ctx, msg);
                                        System.out.println("client read msg : " + msg);
                                    }
                                });
                            }
                        })
                        .connect(address)
                        .sync().channel();
                return (NioSocketChannel) client;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

    }


    public static void sendRpc(byte[] headByte, byte[] contentByte) throws InterruptedException {

//        NioEventLoopGroup work = new NioEventLoopGroup(2);
//        Channel client = new Bootstrap()
//                .channel(NioSocketChannel.class)
//                .group(work)
//                .handler(new ChannelInitializer<NioSocketChannel>() {
//                    @Override
//                    protected void initChannel(NioSocketChannel ch) throws Exception {
//                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
//
//                            @Override
//                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                                super.channelRead(ctx, msg);
//                                System.out.println("client read msg : " + msg);
//                            }
//                        });
//                    }
//                })
//                .connect("localhost", 9999)
//                .sync().channel();

        NioSocketChannel client = ClientFactory.getFactory().getClient(new InetSocketAddress("localhost", 9999));

        ByteBuf buf = ByteBufAllocator.DEFAULT.directBuffer(headByte.length + contentByte.length);
        buf.writeBytes(headByte);
        buf.writeBytes(contentByte);
        client.writeAndFlush(buf);
        System.out.println("rpc request send success ... ");
        client.closeFuture().sync();

    }

    public static class RpcReadChannelHandle extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//            super.channelRead(ctx, msg);
            System.out.println("server read msg : " + msg);

            ByteBuf buf = (ByteBuf) msg;
            // head 122
            byte[] headByte = new byte[122];
            buf.readBytes(headByte);
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(headByte));
            MyHeader myHeader = (MyHeader) in.readObject();
            System.out.println("server read my header is : " + myHeader);

            if (buf.isReadable()) {
                byte[] contentByte = new byte[myHeader.getLength()];
                buf.readBytes(contentByte);
                ObjectInputStream contentIn = new ObjectInputStream(new ByteArrayInputStream(contentByte));
                MyContent content = (MyContent) contentIn.readObject();
                System.out.println("server read my content is : " + content);

            }

        }
    }

    @Test
    public void rpcService() throws InterruptedException {

        ServerBootstrap sbt = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup work = new NioEventLoopGroup(4);
        ChannelFuture server = sbt.group(boss, work)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new RpcReadChannelHandle());
                    }
                }).bind("localhost", 9999).sync();

        System.out.println("server start ... ");
        server.channel().closeFuture().sync();
        System.out.println("server close ... ");

    }

    @Test
    public void runClient() {
        Car car = getRpc(Car.class, new Car() {
            @Override
            public void race(String flag) {
                System.out.println("this is race method... ");
            }
        });
        car.race("sky");

//        System.out.println("this. car : " + car);
    }

    public static void main(String[] args) {

        Car car = getRpc(Car.class, new Car() {
            @Override
            public void race(String flag) {
                System.out.println("this is race method... ");
            }
        });
        System.out.println("this. car : " + car);
    }

}
