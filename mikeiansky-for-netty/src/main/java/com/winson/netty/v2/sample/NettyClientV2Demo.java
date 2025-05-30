package com.winson.netty.v2.sample;

import io.netty.bootstrap.AbstractBootstrap;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author mike ian
 * @date 2025/5/30
 * @desc
 **/
public class NettyClientV2Demo {

    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                System.out.println("client channel is initialized");
            }
        });

        ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("127.0.0.1", 56666));
        channelFuture.channel().closeFuture().sync();

        System.out.println("client application completed");
    }

}
