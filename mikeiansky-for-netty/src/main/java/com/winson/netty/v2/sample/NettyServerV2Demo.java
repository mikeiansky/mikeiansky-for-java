package com.winson.netty.v2.sample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageCodec;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author mike ian
 * @date 2025/5/30
 * @desc
 **/
public class NettyServerV2Demo {

    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 默认情况下，workerGroup的线程数是CPU核心数的两倍
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup);
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                System.out.println("server channel is initialized ... ch : " + ch);
//                ch.pipeline().addLast(); // Add yourhandlers here, e.g., ch.pipeline().addLast(new YourHandler());
                ch.pipeline().addLast(new ByteToMessageCodec<String>() {
                    @Override
                    protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {

                    }

                    @Override
                    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

                    }
                });
                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {

                    @Override
                    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
                        super.handlerAdded(ctx);
                        ctx.write("hello world");
                    }

                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        super.channelRead(ctx, msg);
                    }
                });
            }

        });

        System.out.println("after sleep ... ");
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 56666);
        ChannelFuture channelFuture = serverBootstrap.bind(address);
        System.out.println("after bind ... channelFuture : " + channelFuture);
        channelFuture.channel().closeFuture().sync();

        System.out.println("server application is complete ... ");
    }

}

