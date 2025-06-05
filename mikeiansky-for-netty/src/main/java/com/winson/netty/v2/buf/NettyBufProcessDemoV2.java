package com.winson.netty.v2.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author mike ian
 * @date 2025/6/5
 * @desc
 **/
public class NettyBufProcessDemoV2 {

    public static void main(String[] args) {

        ByteBuf buf = Unpooled.buffer();
        System.out.println(buf.isReadable());
        System.out.println(buf.readableBytes());
        buf.writeInt(1);
        System.out.println(buf.readableBytes());
        buf.writeInt(2);
        System.out.println(buf.readableBytes());
        System.out.println(buf.readerIndex());
        System.out.println(buf.writerIndex());
        System.out.println(buf.isReadable());

        int start = buf.readerIndex();
        int end = buf.writerIndex();

//        for(;start<end;++start) {
//            System.out.println(start);
//        }
        System.out.println("-----");
        System.out.println(buf.getInt(0));
        System.out.println(buf.getInt(4));
        System.out.println(buf.readerIndex());
        System.out.println(buf.writerIndex());
        System.out.println(buf.readableBytes());
        System.out.println(buf.readerIndex(buf.writerIndex()));
        System.out.println(buf.isReadable());
        System.out.println(buf.isContiguous());

        System.out.println("---- empty ");
        System.out.println(Unpooled.EMPTY_BUFFER.isReadable());
        System.out.println(Unpooled.EMPTY_BUFFER.isContiguous());
        System.out.println(Unpooled.EMPTY_BUFFER.release());
        System.out.println(Unpooled.EMPTY_BUFFER.release());
        System.out.println(Unpooled.EMPTY_BUFFER.release());

    }

}
