package com.winson.netty.v2.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import javax.net.ssl.SSLException;
import java.net.InetSocketAddress;
import java.security.cert.CertificateException;

/**
 * @author mike ian
 * @date 2025/6/3
 * @desc
 **/
public class NettyHttpsServerDemoV2 {

    public static void main(String[] args) throws CertificateException, SSLException {

        // 创建自签名证书
        SelfSignedCertificate ssc = new SelfSignedCertificate("localhost");
        SslContext sslCtx = SslContextBuilder
                .forServer(ssc.certificate(), ssc.privateKey())
                .build();

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(sslCtx.newHandler(ch.alloc()));
                ch.pipeline().addLast(new HttpServerCodec());
                ch.pipeline().addLast(new SimpleChannelInboundHandler<DefaultHttpRequest>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, DefaultHttpRequest msg) throws Exception {
                        System.out.println("read msg : " + msg);
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
//        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 60008);
        InetSocketAddress socketAddress = new InetSocketAddress("localhost", 60008);
        bootstrap.bind(socketAddress);

        System.out.println("http server started on " + socketAddress.getHostString() + ":" + socketAddress.getPort());

    }

}
