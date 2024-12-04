package com.winson.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


/**
 * @author winson
 * @date 2022/5/21
 **/
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("EchoServerHandler ---------> channelActive");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        ctx.write(msg);
//        ctx.write("hello client".getBytes(StandardCharsets.UTF_8));
//        System.out.println("msg:class:" + msg.getClass());
//        System.out.println("channelRead msg:" + msg);
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("server receive message : " + byteBuf.toString(Charset.defaultCharset()));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}