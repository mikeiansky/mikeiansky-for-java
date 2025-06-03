package com.winson.netty.v2.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author mike ian
 * @date 2025/6/1
 * @desc
 **/
public class EchoServerDemoV2 {

    public static void main(String[] args) {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.config();

        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                System.out.println("initChannel ch : " + ch);

//                ch.config().setWriteBufferWaterMark(new WriteBufferWaterMark(1024, 1024));
                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {

                    private String content = "";

                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        ByteBuf buf = (ByteBuf) msg;
                        String decodeMsg = buf.toString(Charset.defaultCharset());
                        if (decodeMsg.contains("end")) {
                            content += decodeMsg.replace("end", "");
//                            System.out.println("server receive complete msg : " + content);
                            super.channelRead(ctx, content);
                            content = "";
                        } else {
                            System.out.println("server receive fragment msg : " + decodeMsg);
                            content += decodeMsg;
                        }
//                        super.channelRead(ctx, msg);
                    }
                });
                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        System.out.println("server complete msg : " + msg);
                        super.channelRead(ctx, msg);
                    }
                });

                new Thread(() -> {
                    System.out.println("start server echo ... ");
                    boolean stop = false;
                    while (!stop) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                        try {
                            String msg = reader.readLine();
                            System.out.println("server send msg : " + msg);
                            byte[] data = msg.getBytes(Charset.defaultCharset());
                            final ByteBuf buf = ch.alloc().buffer(data.length);
                            buf.writeBytes(data);
                            ch.writeAndFlush(buf);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("stop server echo ... ");
                }).start();

            }
        });

        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 60003);
        ChannelFuture channelFuture = bootstrap.bind(address);

    }

}
