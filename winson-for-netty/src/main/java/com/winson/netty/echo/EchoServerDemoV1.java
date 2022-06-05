package com.winson.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author winson
 * @date 2022/5/21
 **/
public class EchoServerDemoV1 {

    public static void main(String[] args) throws InterruptedException {
//        EventLoopGroup bossGroup1 = new NioEventLoopGroup(1, new ThreadFactory() {
//            private AtomicLong id = new AtomicLong();
//            @Override
//            public Thread newThread(Runnable r) {
//                return new Thread(r, "winson-boss-"+id.incrementAndGet());
//            }
//        });
//        EventLoopGroup workGroup1 = new NioEventLoopGroup(new ThreadFactory() {
//            private AtomicLong id = new AtomicLong();
//
//            @Override
//            public Thread newThread(Runnable r) {
//                return new Thread(r, "winson-worker-"+id.incrementAndGet());
//            }
//        });

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        final EchoServerHandler serverHandler = new EchoServerHandler();
//        bossGroup.submit(() -> System.out.println("do submit task "));
//        bossGroup.execute(() -> System.out.println("do execute task "));

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
//                .childOption()
                .option(ChannelOption.SO_BACKLOG, 100)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addFirst(new ChannelHandler() {
                            @Override
                            public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
                                AdaptiveRecvByteBufAllocator rba = new AdaptiveRecvByteBufAllocator(10, 10, 10);
                                ctx.channel().config().setRecvByteBufAllocator(rba);
                            }

                            @Override
                            public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

                            }

                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

                            }
                        });
                        socketChannel.pipeline().addLast(serverHandler);
                    }
                });

        Channel channel = b.bind(8007).channel();
        channel.pipeline().addLast(new ChannelHandler() {
            @Override
            public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
                System.out.println("handle1 ---------- handlerAdded");

            }

            @Override
            public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

            }

            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                System.out.println("exceptionCaught ---------- 1");
            }
        });
        channel.pipeline().addLast(new ChannelHandler() {
            @Override
            public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
                System.out.println("handle2 ---------- handlerAdded");

            }

            @Override
            public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

            }

            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                System.out.println("exceptionCaught ---------- 2");
            }
        });

//        AdaptiveRecvByteBufAllocator rba = new AdaptiveRecvByteBufAllocator(10, 10, 10);
//        channel.config().setRecvByteBufAllocator(rba);
        channel.closeFuture().sync();
        channel.close();
//        bossGroup.shutdownGracefully();
//        workGroup.shutdownGracefully();

    }

}
