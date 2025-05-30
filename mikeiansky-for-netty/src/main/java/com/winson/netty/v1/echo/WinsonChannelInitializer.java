package com.winson.netty.v1.echo;

import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;

/**
 * @author winson
 * @date 2022/6/10
 **/
public class WinsonChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println(" ---------> WinsonChannelInitializer channelActive -------> ");
    }


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        System.out.println("child handler initChannel -------> ");
//        socketChannel.pipeline().addFirst(new ChannelHandler() {
//
//
//            @Override
//            public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//                AdaptiveRecvByteBufAllocator rba = new AdaptiveRecvByteBufAllocator(10, 10, 10);
//                ctx.channel().config().setRecvByteBufAllocator(rba);
//            }
//
//            @Override
//            public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//
//            }
//
//            @Override
//            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//
//            }
//        });
//        socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter(){
//            @Override
//            public void channelActive(ChannelHandlerContext ctx) throws Exception {
//                super.channelActive(ctx);
//                System.out.println("child channel inbound handle active ... ");
//            }
//        });
        socketChannel.pipeline().addLast(new EchoServerHandler());
    }

}
