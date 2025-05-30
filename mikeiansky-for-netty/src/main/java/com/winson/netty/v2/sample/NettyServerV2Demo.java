package com.winson.netty.v2.sample;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author mike ian
 * @date 2025/5/30
 * @desc
 **/
public class NettyServerV2Demo {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors() * 2);

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                System.out.println("server channel is initialized ... ");
//                ch.pipeline().addLast(); // Add yourhandlers here, e.g., ch.pipeline().addLast(new YourHandler());
            }

        });
        ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress("127.0.0.1", 56666));
        channelFuture.channel().closeFuture().sync();

        System.out.println("server application is complete ... ");
    }

}

