package com.winson.framework.netty.v3;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author winson
 * @date 2021/8/28
 **/
public class MyNetty {

    @Test
    public void testByteBuf() {
        System.out.println("test byte buf ");

        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.directBuffer(8, 20);
        printByteBuf(byteBuf);
        byteBuf.writeBytes(new byte[]{1, 2, 3, 4});
        printByteBuf(byteBuf);
        byteBuf.writeBytes(new byte[]{1, 2, 3, 4});
        printByteBuf(byteBuf);
        byteBuf.writeBytes(new byte[]{1, 2, 3, 4});
        printByteBuf(byteBuf);
        byteBuf.writeBytes(new byte[]{1, 2, 3, 4});
        printByteBuf(byteBuf);

    }

    public static void printByteBuf(ByteBuf buf) {
        System.out.println("---------");
        System.out.println("buf.capacity : " + buf.capacity());
        System.out.println("buf.maxCapacity : " + buf.maxCapacity());
        System.out.println("buf.isDirect : " + buf.isDirect());
        System.out.println("buf.isReadable : " + buf.isReadable());
        System.out.println("buf.readerIndex : " + buf.readerIndex());
        System.out.println("buf.readableBytes : " + buf.readableBytes());
        System.out.println("buf.isWritable : " + buf.isWritable());
        System.out.println("buf.writerIndex : " + buf.writerIndex());
        System.out.println("buf.writableBytes : " + buf.writableBytes());
    }

    @Test
    public void testByteBuf2() {
        ByteBuf buf = ByteBufAllocator.DEFAULT.directBuffer(1024);

        String msg = "hello";

        buf.writeBytes(msg.getBytes(StandardCharsets.UTF_8));

        printByteBuf(buf);

        CharSequence cs = buf.getCharSequence(0, buf.readableBytes(), StandardCharsets.UTF_8);
        System.out.println("read cs : " + cs);

    }

    @Test
    public void loopGroup() {
        NioEventLoopGroup group = new NioEventLoopGroup(2);
        group.execute(() -> {
            for (; ; ) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " runnable 1 ... ");
            }

        });
        group.execute(() -> {
            for (; ; ) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " runnable 2 ... ");
            }
        });
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("------ finish ------");
    }

    @Test
    public void clientMode() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();

        NioSocketChannel client = new NioSocketChannel();
        group.register(client);


        client.connect(new InetSocketAddress("192.168.204.130", 9999));
        System.out.println("connect success ");

        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.directBuffer(1024);
        byteBuf.writeBytes("hello".getBytes());

        client.writeAndFlush(byteBuf);

        System.out.println("write success ");


        client.closeFuture().sync();
        System.out.println("client mode finish ");

    }

    @Test
    public void serverMode() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        NioServerSocketChannel server = new NioServerSocketChannel();
        group.register(server);
        final MyInitChannelHandle initChannelHandle = new MyInitChannelHandle();
        server.bind(new InetSocketAddress("192.168.204.1", 9999));

        server.pipeline().addLast(new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                super.channelRead(ctx, msg);

                System.out.println("read msg ---> : " + msg);
                NioSocketChannel socketChannel = (NioSocketChannel) msg;
                socketChannel.pipeline().addLast(initChannelHandle);

                group.register(socketChannel);

            }
        });

//        server.pipeline().addLast(new RegisterChannel(group));

        System.out.println("server bind success");
        server.closeFuture().sync();
    }

    @ChannelHandler.Sharable
    public static class MyInitChannelHandle extends ChannelInboundHandlerAdapter {

//        private NioEventLoopGroup selector;
//
//        public MyInitChannelHandle(NioEventLoopGroup selector) {
//            this.selector = selector;
//        }


        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            super.channelRegistered(ctx);
            System.out.println("MyInitChannelHandle ----> channelRegistered");

            ctx.channel().pipeline().addLast(new ChannelRead());
            ctx.channel().pipeline().remove(this);
        }

//        @Override
//        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//            //            super.channelRead(ctx, msg);
//            ByteBuf buf = (ByteBuf) msg;
////            printByteBuf(buf);
//            CharSequence str = buf.getCharSequence(0, buf.readableBytes(), StandardCharsets.UTF_8);
//            System.out.println("socket read ---> msg : " + str);
////            System.out.println("client read msg : " + msg);
//
//            ctx.writeAndFlush(buf);
//        }
    }

    public static class ChannelRead extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//            super.channelRead(ctx, msg);
            ByteBuf buf = (ByteBuf) msg;
//            printByteBuf(buf);
            CharSequence str = buf.getCharSequence(0, buf.readableBytes(), StandardCharsets.UTF_8);
            System.out.println("socket read ---> msg : " + str);
//            System.out.println("client read msg : " + msg);

            ctx.writeAndFlush(buf);

        }
    }

    @Test
    public void nettyClient() throws Exception {

        NioEventLoopGroup group = new NioEventLoopGroup();
//        NioSocketChannel client = new NioSocketChannel();
//        group.register(client);

        Bootstrap bs = new Bootstrap();
        bs.group(group);
        bs.channel(NioSocketChannel.class);
        bs.handler(new ChannelInitializer<NioSocketChannel>() {

            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                System.out.println("init channel");
                ch.pipeline().addLast(new ChannelRead());
            }

        });

//        client.writeAndFlush();

        ChannelFuture cf = bs.connect(new InetSocketAddress("192.168.204.130", 9999));
        cf.sync();
        System.out.println("connect success ... ");

//        cf.channel().pipeline().addLast(new ChannelRead());

        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.directBuffer(1024);
        byteBuf.writeBytes("hello".getBytes());

        cf.channel().writeAndFlush(byteBuf);

        System.out.println("write success ");


//        client.closeFuture().sync();
        cf.channel().closeFuture().sync();
        System.out.println("netty client finish ... ");

    }

    @Test
    public void nettyServer() throws Exception {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup work = new NioEventLoopGroup(4);
        ServerBootstrap sb = new ServerBootstrap();
        sb.group(boss, work);

        MyInitChannelHandle myInitChannelHandle = new MyInitChannelHandle();
        ChannelFuture server = sb.channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        System.out.println("child handle init channel : " + ch);

//                        ch.pipeline().addLast(new MyInitChannelHandle());
                        ch.pipeline().addLast(myInitChannelHandle);

                    }
                })
                .bind("192.168.204.1", 9999).sync();

        System.out.println("netty server start ... ");

        server.channel().closeFuture().sync();
        System.out.println("netty server finish ... ");

    }

}
