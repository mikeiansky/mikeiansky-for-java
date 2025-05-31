package com.winson.netty.v2.sample;

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

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 默认情况下，workerGroup的线程数是CPU核心数的两倍
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                System.out.println("server channel is initialized ... ch : " + ch);
//                ch.pipeline().addLast(); // Add yourhandlers here, e.g., ch.pipeline().addLast(new YourHandler());
            }

        });

        System.out.println("after sleep ... ");
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 56666);
        ChannelFuture channelFuture = serverBootstrap.bind(address);
        System.out.println("after bind ... ");
        channelFuture.channel().closeFuture().sync();

        System.out.println("server application is complete ... ");
    }

}

