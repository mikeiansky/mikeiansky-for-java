package com.winson.framework.netty.rpc_v3;

import com.winson.utils.common.SerUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.http.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author winson
 * @date 2021/8/30
 **/
public class MyNettyRpcV3 {

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
        private Class[] paramTypes;
        private Object[] args;

        public Class[] getParamTypes() {
            return paramTypes;
        }

        public void setParamTypes(Class[] paramTypes) {
            this.paramTypes = paramTypes;
        }

        public Object[] getArgs() {
            return args;
        }

        public void setArgs(Object[] args) {
            this.args = args;
        }

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
                                nioSocketChannel.pipeline().addLast(new ObjectChannelHandle("client " + index));
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

    public static class RequestDispatch {

        public static final ConcurrentHashMap<String, Object> dis = new ConcurrentHashMap<>();

        public void register(String key, Object obj) {
            dis.put(key, obj);
        }

        public Object getDispatch(String key) {
            return dis.get(key);
        }

    }


    public static class ClientReadChannelHandle extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            MyNettyRpcV3.PackMessage packMessage = (MyNettyRpcV3.PackMessage) msg;
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

                MyContent content = new MyContent();
                content.setName(clazz.getName());
                content.setMethod(method.getName());
                content.setParamTypes(method.getParameterTypes());
                content.setArgs(args);


//                Object arg0 = args[0];
//                content.setContent((String) arg0);


                byte[] contentBuf = SerUtil.encode(content);

                MyHeader header = new MyHeader();
                header.setRequestId(Math.abs(UUID.randomUUID().getLeastSignificantBits()));
                header.setFlag(0x141414);
                header.setContentLength(contentBuf.length);

                byte[] headBuf = SerUtil.encode(header);
                CompletableFuture<Object> future = new CompletableFuture<>();

//                urlST(contentBuf, future);
                nettyST(contentBuf, future);


                return future.get();


//                CompletableFuture<String> future = new CompletableFuture<>();
//                RequestCallback.addRequestCallback(header.getRequestId(), future);

//                ByteBuf buf = ByteBufAllocator.DEFAULT.directBuffer(contentBuf.length);
//                buf.writeBytes(headBuf);
//                buf.writeBytes(contentBuf);
//                channel.writeAndFlush(buf);

//                return future.get();
//                return null;
            }
        });
    }

    public static void main(String[] args) {

//        for (int i = 0; i < 100; i++) {
//            int finalI = i;
//            new Thread(() -> {
//
//                Car car = getProxy(Car.class);
//                String sendMessage = "hello : " + finalI;
//                String res = car.race(sendMessage);
//                System.out.println("response msg  " + res + " , client send msg : " + sendMessage);
//
//            }).start();
//        }
//
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Car car = getProxy(Car.class);
        String sendMessage = "hello car";
        String res = car.race(sendMessage);
        System.out.println("response msg  " + res + " , client send msg : " + sendMessage);

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("app end xxx ");
    }


    public static class HttpClientReadChannelHandle extends ChannelInboundHandlerAdapter {

        private CompletableFuture<Object> future;

        public HttpClientReadChannelHandle(CompletableFuture<Object> future) {
            this.future = future;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//            MyNettyRpcV3.PackMessage packMessage = (MyNettyRpcV3.PackMessage) msg;
//            RequestCallback.callback(packMessage.header.requestId, packMessage.content.content);
            System.out.println(" http client read result msg : " + msg);


            FullHttpResponse response = (FullHttpResponse) msg;
            byte[] cs = new byte[response.headers().getInt("content-length")];
            response.content().readBytes(cs);
            MyContent rc = SerUtil.decode(cs, MyContent.class);
            System.out.println("client decode content : " + rc);

            future.complete(rc.getContent());
        }
    }

    public static void urlST(byte[] content, CompletableFuture<Object> future) {
        try {
            URL url = new URL("http://localhost:9999");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod(HttpMethod.POST.name());

            connection.setRequestProperty("content-length", String.valueOf(content.length));
            connection.getOutputStream().write(content);

            if (connection.getResponseCode() == 200) {
                System.out.println("connect success ... ");

                int contentLength = Integer.parseInt(connection.getHeaderField("content-length"));
                byte[] contents = new byte[contentLength];
                connection.getInputStream().read(contents);

                MyContent responseContent = SerUtil.decode(contents, MyContent.class);
                future.complete(responseContent.getContent());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void nettyST(byte[] content, CompletableFuture<Object> future){
        try {
            Channel client = new Bootstrap()
                    .group(new NioEventLoopGroup(1))
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new HttpClientCodec())
                                    .addLast(new HttpObjectAggregator(1024 * 513))
                                    .addLast(new HttpClientReadChannelHandle(future));
                        }
                    })
                    .connect(new InetSocketAddress("localhost", 9999))
                    .sync()
                    .channel();


            FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0,
                    HttpMethod.POST, "");
            request.headers().add("content-length", content.length);
            request.content().writeBytes(content);

            client.writeAndFlush(request);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
