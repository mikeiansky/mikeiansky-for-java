package com.winson.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
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
        bossGroup.execute(() -> System.out.println("do execute task "));

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(serverHandler);
                    }
                });

        b.bind(8007).channel().closeFuture().sync();

//        bossGroup.shutdownGracefully();
//        workGroup.shutdownGracefully();

    }

}
