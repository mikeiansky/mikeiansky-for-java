package com.winson.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author winson
 * @date 2022/5/21
 **/
public class EchoServerDemoV1 {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        final EchoServerHandler serverHandler = new EchoServerHandler();
//        bossGroup.submit(() -> System.out.println("do submit task "));
//        bossGroup.execute(() -> System.out.println("do execute task "));

        ChannelInitializer<SocketChannel> channelInitializer = new ChannelInitializer<SocketChannel>() {

            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                super.channelActive(ctx);
                System.out.println(" ---------> child handler channelActive -------> ");
            }


            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                System.out.println("child handler initChannel -------> ");
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
                socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        super.channelActive(ctx);
                        System.out.println("child channel inbound handle active ... ");
                    }
                });
                socketChannel.pipeline().addLast(serverHandler);
            }
        };

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
//                .childOption()
                .option(ChannelOption.SO_BACKLOG, 1)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(channelInitializer);

        ChannelFuture channelFuture = b.bind(8007);
//        channelFuture.channel().pipeline().addFirst();
        channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                System.out.println("operationComplete -----------> ...... future : " + future);
            }
        });
//        channelFuture.addListener(new MyErrorGenericFutureListener());

        channelFuture.channel().pipeline().addLast(new ChannelInboundHandlerAdapter(){
            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                super.channelActive(ctx);
                System.out.println("server ---------- channelActive 001 ");
//                ctx.fireChannelActive();
            }
        });

        channelFuture.channel().pipeline().addLast(new ChannelInboundHandlerAdapter(){
            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                super.channelActive(ctx);
                System.out.println("server ---------- channelActive 002 ");
            }
        });

//        AdaptiveRecvByteBufAllocator rba = new AdaptiveRecvByteBufAllocator(10, 10, 10);
//        channel.config().setRecvByteBufAllocator(rba);
        channelFuture.channel().closeFuture().sync();
        channelFuture.channel().close();
//        bossGroup.shutdownGracefully();
//        workGroup.shutdownGracefully();

    }

}
