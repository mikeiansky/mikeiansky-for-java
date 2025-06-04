package com.winson.netty.v2.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedInput;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.net.InetSocketAddress;

/**
 * @author mike ian
 * @date 2025/6/4
 * @desc
 **/
public class NettyHttpChunkServerDemo {

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
                ch.pipeline().addLast(new ChunkedWriteHandler());
                ch.pipeline().addLast(new SimpleChannelInboundHandler<HttpObject>() {
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
                        System.out.println("Received message: " + msg);
                        if (msg instanceof HttpRequest) {
                            DefaultHttpResponse response = new DefaultHttpResponse(
                                    HttpVersion.HTTP_1_1,
                                    io.netty.handler.codec.http.HttpResponseStatus.OK);
                            response.headers().set(HttpHeaderNames.TRANSFER_ENCODING, "chunked");
                            ctx.write(response);

                            ChunkedInput<ByteBuf> chunkedInput = new ChunkedInput<>() {
                                private int count = 0;
                                private final int max = 5;

                                @Override
                                public boolean isEndOfInput() throws Exception {
                                    return count >= max;
                                }

                                @Override
                                public void close() throws Exception {
                                }

                                @Override
                                public ByteBuf readChunk(ChannelHandlerContext ctx) throws Exception {
                                    if (isEndOfInput()) {
                                        System.out.println("readChunk end of input 001");
                                        return null;
                                    }
                                    System.out.println("readChunk 001 ");
                                    String content = "chunk-" + (++count) + "\n";
                                    return ctx.alloc().buffer().writeBytes(content.getBytes());
                                }

                                @Override
                                public ByteBuf readChunk(ByteBufAllocator allocator) throws Exception {
                                    if (isEndOfInput()) {
                                        System.out.println("readChunk end of input 002");
                                        return null;
                                    }
                                    System.out.println("readChunk 002 ");
                                    String content = "chunk-" + (++count) + "\n";
                                    return allocator.buffer().writeBytes(content.getBytes());
                                }

                                @Override
                                public long length() {
                                    return -1;
                                }

                                @Override
                                public long progress() {
                                    return count;
                                }
                            };
                            ctx.writeAndFlush(new HttpChunkedInput(chunkedInput));
                        }
                    }
                });
            }
        });

        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 60007);
        bootstrap.bind(socketAddress);
        System.out.println("http server started on " + socketAddress.getHostString() + ":" + socketAddress.getPort());

    }

}
