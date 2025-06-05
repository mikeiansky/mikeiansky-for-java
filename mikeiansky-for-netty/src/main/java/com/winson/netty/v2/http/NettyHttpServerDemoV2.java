package com.winson.netty.v2.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http2.Http2ConnectionHandler;
import io.netty.handler.codec.http2.Http2MultiplexHandler;
import io.netty.handler.proxy.HttpProxyHandler;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author mike ian
 * @date 2025/6/3
 * @desc
 **/
public class NettyHttpServerDemoV2 {

    public static void main(String[] args) {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new HttpServerCodec());
                ch.pipeline().addLast(new SimpleChannelInboundHandler<HttpObject>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
                        ctx.fireChannelRead(msg);
                        if (msg instanceof HttpRequest request) {
                            System.out.println("Received HTTP request: " + request.uri());
                        } else if (msg instanceof HttpContent content) {
//                            content.content();
                            System.out.println("Received HTTP content: " + content);
                        }
                    }
                });
                ch.pipeline().addLast(new HttpObjectAggregator(65536)); // 聚合HTTP消息
                ch.pipeline().addLast(new SimpleChannelInboundHandler<FullHttpRequest>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
                        System.out.println("read msg : " + msg + " , msg.getClass() : " + msg.getClass());
                        String responseContent = "Hello, Netty HTTP!";
                        FullHttpResponse response = new DefaultFullHttpResponse(
                                HttpVersion.HTTP_1_1,
                                HttpResponseStatus.OK,
                                ctx.alloc().buffer().writeBytes(responseContent.getBytes())
                        );
                        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
                        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, responseContent.length());
                        ctx.writeAndFlush(response);
                    }
                });
            }
        });
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 60006);
        bootstrap.bind(socketAddress);

        System.out.println("http server started on " + socketAddress.getHostString() + ":" + socketAddress.getPort());

    }

}
