package com.winson.netty.v2.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author mike ian
 * @date 2025/6/5
 * @desc
 **/
public class NettyBufCumulationDemo {

    public static void main(String[] args) {

        ByteBuf buf1 = Unpooled.buffer();
        buf1.writeBytes("Hello, ".getBytes());
        ByteBuf buf2 = Unpooled.buffer();
        buf2.writeBytes("world".getBytes());
        buf1.writeBytes(buf2, buf2.readerIndex(), buf2.readableBytes());
        byte[] bytes = new byte[buf1.readableBytes()];
        buf1.readBytes(bytes);
        System.out.println(new String(bytes));

    }

}
