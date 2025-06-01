package com.winson.netty.v2.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @author mike ian
 * @date 2025/6/1
 * @desc
 **/
public class EchoClientDemoV2 {

    public static void main(String[] args) {


        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.group(group);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                System.out.println("initChannel ch : " + ch);
                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        ByteBuf buf = (ByteBuf) msg;
                        String decodeMsg = buf.toString(Charset.defaultCharset());
                        System.out.println("client receive msg : " + decodeMsg);
                        super.channelRead(ctx, msg);
                    }
                });
                ch.writeAndFlush("Hello this is client demo");
            }
        });

        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 60003);
        ChannelFuture channelFuture = bootstrap.connect(address);
//        channelFuture.channel().closeFuture().syncUninterruptibly();
        channelFuture.addListener((ChannelFutureListener) future -> {
            System.out.println("client connect success : " + future.isSuccess());
            if (future.isSuccess()) {
                new Thread(() -> {
                    System.out.println("start client echo");
                    Channel channel = future.channel();
                    boolean stop = false;
                    while (!stop) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                        try {
                            String msg = reader.readLine();
                            System.out.println("client send msg : " + msg);
//                            ByteBuf buf1 = PooledByteBufAllocator.DEFAULT.buffer();
                            byte[] data = msg.getBytes(Charset.defaultCharset());
                            final ByteBuf buf = channel.alloc().buffer(data.length);
                            buf.writeBytes(data,0, data.length);
                            channel.writeAndFlush(buf);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("stop client echo");
                }).start();
            }
        });


    }

}
