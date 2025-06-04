package com.winson.netty.v2.ws;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.ssl.ClientAuth;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import javax.net.ssl.SSLException;
import java.net.InetSocketAddress;
import java.security.cert.CertificateException;

/**
 * @author mike ian
 * @date 2025/6/4
 * @desc
 **/
public class NettyWssSeverDemoV2 {

    public static void main(String[] args) throws CertificateException, SSLException {

        // 自签名证书，可以使用https://dns/app 进行手动通过信任后，通过wss进行连接测试
        // 创建自签名证书
        SelfSignedCertificate ssc = new SelfSignedCertificate("localhost");
        SslContext sslCtx = SslContextBuilder
                .forServer(ssc.certificate(), ssc.privateKey())
                .clientAuth(ClientAuth.NONE)
                .build();

        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        System.out.println("accept channel : " + ch);
                        ch.pipeline().addFirst("ssl", sslCtx.newHandler(ch.alloc()));
                        ch.pipeline().addLast(new HttpServerCodec());
                        ch.pipeline().addLast(new HttpObjectAggregator(65536));
                        // 这里需要添加SSL处理器，实际部署时请替换为你自己的SSL上下文
                        ch.pipeline().addLast(new WebSocketServerProtocolHandler("/app"));
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<TextWebSocketFrame>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
                                System.out.println("收到消息: " + msg.text());
                                ctx.channel().writeAndFlush(new TextWebSocketFrame("服务端收到: " + msg.text()));
                            }
                        });
                    }
                });
        bootstrap.bind(new InetSocketAddress("localhost", 60011));
        System.out.println("wss 服务端已启动，端口: 60011");
    }

}
