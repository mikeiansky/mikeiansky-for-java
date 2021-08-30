package com.winson.framework.netty.rpc_v2;

import com.winson.utils.common.SerUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author winson
 * @date 2021/8/30
 **/
public class MyNettyRpcV2 {

    public interface Car {
        String race(String mark);
    }

    // 127
    public static class MyHeader implements Serializable {

        private int flag;
        private long requestId;
        private int contentLength;

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

        public int getContentLength() {
            return contentLength;
        }

        public void setContentLength(int contentLength) {
            this.contentLength = contentLength;
        }

        @Override
        public String toString() {
            return "MyHeader{" +
                    "flag=" + flag +
                    ", requestId=" + requestId +
                    ", contentLength=" + contentLength +
                    '}';
        }
    }

    public static class MyContent implements Serializable {
        private String name;
        private String method;
        private String content;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "MyContent{" +
                    "name='" + name + '\'' +
                    ", method='" + method + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }

    public static class ClientFactory {

        private static final ClientFactory clientFactory = new ClientFactory();

        private ConcurrentHashMap<InetSocketAddress, ClientPool> clientPoolMap = new ConcurrentHashMap<InetSocketAddress, ClientPool>();

        private ClientFactory() {

        }

        public static ClientFactory getInstance() {
            return clientFactory;
        }

        public Channel getChannel(String host, int port) {
            InetSocketAddress address = new InetSocketAddress(host, port);
            ClientPool clientPool = clientPoolMap.get(address);
            if (clientPool == null) {
                clientPool = new ClientPool(5);
                clientPoolMap.put(address, clientPool);
            }
            return clientPool.getChannel(address);
        }

    }

    public static class ClientPool {

        private int size;
        private Object[] synObj;
        private Channel[] client;
        private AtomicInteger cid = new AtomicInteger();

        public ClientPool(int size) {
            this.size = size;
            client = new Channel[size];
            synObj = new Object[size];
            for (int i = 0; i < size; i++) {
                synObj[i] = new Object();
            }
        }

        public Channel getChannel(InetSocketAddress address) {
            int index = cid.incrementAndGet() % client.length;
            if (client[index] == null) {
                synchronized (synObj[index]) {
                    if (client[index] == null) {
                        client[index] = createChannel(address, index);
                    }
                }
            }
            return client[index];
        }

        private Channel createChannel(InetSocketAddress address, int index) {
            NioEventLoopGroup work = new NioEventLoopGroup(1);
            try {
                return new Bootstrap()
                        .group(work)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
//                                nioSocketChannel.pipeline().addLast(new ServerDecode());
                                nioSocketChannel.pipeline().addLast(new ObjectChannelHandle("client "+ index));
                                nioSocketChannel.pipeline().addLast(new ClientReadChannelHandle());
                            }
                        })
                        .connect(address)
                        .sync().channel();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    public static void startServer() throws Exception {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup work = new NioEventLoopGroup(4);
        Channel server = new ServerBootstrap()
                .group(boss, work)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        System.out.println(" connect client : " + nioSocketChannel);
//                        nioSocketChannel.pipeline().addLast(new ServerDecode());
                        nioSocketChannel.pipeline().addLast(new ObjectChannelHandle("server"));
                        nioSocketChannel.pipeline().addLast(new ServerReadChannelHandle());
                    }
                })
                .bind("localhost", 9999)
                .sync()
                .channel();
        System.out.println(" server started ... ");


        server.closeFuture().sync();
        System.out.println(" server finish xxx ");
    }

    public static class PackMessage {
        public MyHeader header;
        public MyContent content;

        public PackMessage() {

        }

        public PackMessage(MyHeader header, MyContent content) {
            this.header = header;
            this.content = content;
        }

        @Override
        public String toString() {
            return "PackMessage{" +
                    "header=" + header +
                    ", content=" + content +
                    '}';
        }
    }

    public static class ObjectChannelHandle extends ByteToMessageDecoder {

        public String name;

        public ObjectChannelHandle(String name) {
            this.name = name;
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            System.err.println("exception on ------> " + name);
            super.exceptionCaught(ctx, cause);

        }

        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> list) throws Exception {
//            System.out.println(name+ " , rpc read channel read msg : " + msg.readableBytes());
            ByteBuf buf = (ByteBuf) msg;
            while (buf.readableBytes() >= 127) {
                byte[] bs = new byte[127];
                buf.getBytes(buf.readerIndex(), bs);
//                buf.readBytes(bs);
                MyHeader myHeader = SerUtil.decode(bs, MyHeader.class);
//                System.out.println("rpc read header info : " + myHeader);
                if (buf.readableBytes() >= (myHeader.contentLength + 127)) {
//                    if(!name.startsWith("server")){
//                        System.out.println(name + " readableBytes : " + buf.readableBytes() + " , readIndex : " + buf.readerIndex() + " , wbs : " + buf.writableBytes() + " , content length : " + myHeader.contentLength);
//                    }
                    buf.readBytes(127);
                    byte[] cbs = new byte[myHeader.contentLength];
                    buf.readBytes(cbs);
                    MyContent content = SerUtil.decode(cbs, MyContent.class);
//                    System.out.println("rpc read content info : " + content);

                    PackMessage packMessage = new PackMessage();
                    packMessage.header = myHeader;
                    packMessage.content = content;
                    list.add(packMessage);

                } else {
                    break;
                }

            }
        }

    }

    public static class ServerDecode extends ByteToMessageDecoder {

        //父类里一定有channelread{  前老的拼buf  decode（）；剩余留存 ;对out遍历 } -> bytebuf
        //因为你偷懒，自己能不能实现！
        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {

            while (buf.readableBytes() >= 127) {
                byte[] bytes = new byte[127];
                buf.getBytes(buf.readerIndex(), bytes);  //从哪里读取，读多少，但是readindex不变
                ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                ObjectInputStream oin = new ObjectInputStream(in);
                MyHeader header = (MyHeader) oin.readObject();


                //DECODE在2个方向都使用
                //通信的协议
                if (buf.readableBytes() >= header.getContentLength()) {
                    //处理指针
                    buf.readBytes(127);  //移动指针到body开始的位置
                    byte[] data = new byte[(int) header.getContentLength()];
                    buf.readBytes(data);
                    ByteArrayInputStream din = new ByteArrayInputStream(data);
                    ObjectInputStream doin = new ObjectInputStream(din);

                    if (header.getFlag() == 0x141414) {
                        MyContent content = (MyContent) doin.readObject();
                        out.add(new PackMessage(header, content));

                    } else if (header.getFlag() == 0x141424) {
                        MyContent content = (MyContent) doin.readObject();
                        out.add(new PackMessage(header, content));
                    }


                } else {
                    break;
                }


            }

        }
    }

    public static class ServerReadChannelHandle extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            PackMessage packMessage = (PackMessage) msg;
//            System.out.println(" server read message : " + packMessage);

            // 这里直接写会有问题
            ctx.executor().execute(new Runnable() {
                @Override
                public void run() {
                    MyContent responseContent = new MyContent();
                    responseContent.setContent("server response " + packMessage.content.getContent());
                    byte[] respContentBuf = SerUtil.encode(responseContent);

                    MyHeader responseHeader = new MyHeader();
                    responseHeader.setRequestId(packMessage.header.getRequestId());
                    responseHeader.setFlag(0x141424);
                    responseHeader.setContentLength(respContentBuf.length);


                    byte[] respHeadBuf = SerUtil.encode(responseHeader);
//                    System.out.println("write response header content length : " + respHeadBuf.length);

                    ByteBuf responseBuf = ByteBufAllocator.DEFAULT.directBuffer(respHeadBuf.length + respContentBuf.length);
                    responseBuf.writeBytes(respHeadBuf);
                    responseBuf.writeBytes(respContentBuf);
                    ctx.writeAndFlush(responseBuf);

//                    MyContent content = new MyContent();
//                    content.setContent(" response from server . ");
//
//                    byte[] contentByte = SerUtil.encode(content);
//
////                    System.out.println(" server content length : " + contentByte.length);
//
//                    MyHeader responseHeader = new MyHeader();
//                    responseHeader.setFlag(0x141424);
//                    responseHeader.setRequestId(packMessage.header.getRequestId());
//                    responseHeader.setContentLength(contentByte.length);
//                    byte[] headByte = SerUtil.encode(responseHeader);
//                    ByteBuf responseBuf = ByteBufAllocator.DEFAULT.directBuffer(headByte.length + contentByte.length);
//                    responseBuf.writeBytes(headByte);
//                    responseBuf.writeBytes(contentByte);
//                    ctx.writeAndFlush(responseBuf);
                }
            });
        }

    }

    public static class ClientReadChannelHandle extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            MyNettyRpcV2.PackMessage packMessage = (MyNettyRpcV2.PackMessage) msg;
            RequestCallback.callback(packMessage.header.requestId, packMessage.content.content);
        }
    }


    public static class RequestCallback {
        private static ConcurrentHashMap<Long, CompletableFuture<String>> requestCallback = new ConcurrentHashMap<>();

        public static void addRequestCallback(long requestId, CompletableFuture<String> future) {
            requestCallback.put(requestId, future);
        }

        public static void callback(long requestId, String res) {
            CompletableFuture<String> cf = requestCallback.get(requestId);
            cf.complete(res);
            requestCallback.remove(requestId);
        }
    }


    public static <T> T getProxy(Class<T> clazz) {

        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                System.out.println("invoke method : " + method);
                Channel channel = ClientFactory.getInstance().getChannel("localhost", 9999);

                Object arg0 = args[0];

                MyContent content = new MyContent();
                content.setName(clazz.getName());
                content.setMethod(method.getName());
                content.setContent((String) arg0);
                byte[] contentBuf = SerUtil.encode(content);

                MyHeader header = new MyHeader();
                header.setRequestId(Math.abs(UUID.randomUUID().getLeastSignificantBits()));
                header.setFlag(0x141414);
                header.setContentLength(contentBuf.length);

                byte[] headBuf = SerUtil.encode(header);

                CompletableFuture<String> future = new CompletableFuture<>();
                RequestCallback.addRequestCallback(header.getRequestId(), future);

                ByteBuf buf = ByteBufAllocator.DEFAULT.directBuffer(contentBuf.length);
                buf.writeBytes(headBuf);
                buf.writeBytes(contentBuf);
                channel.writeAndFlush(buf);

                return future.get();
            }
        });
    }

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                startServer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();


        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(() -> {

                Car car = getProxy(Car.class);
                String sendMessage = "hello : " + finalI;
                String res = car.race(sendMessage);
                System.out.println("response msg  " + res + " , client send msg : " + sendMessage);

            }).start();
        }

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("app end xxx ");
    }

}
