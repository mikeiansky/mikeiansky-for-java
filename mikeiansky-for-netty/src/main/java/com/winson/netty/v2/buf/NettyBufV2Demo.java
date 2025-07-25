package com.winson.netty.v2.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

import java.nio.charset.Charset;

/**
 * @author mike ian
 * @date 2025/5/30
 * @desc
 **/
public class NettyBufV2Demo {

    public static void main(String[] args) {


        PooledByteBufAllocator allocator = new PooledByteBufAllocator(true);
        ByteBuf buf = allocator.heapBuffer(8, 16);
        buf = allocator.buffer();
        System.out.println("------ before operation ------");
        System.out.println("buf.capacity() : " + buf.capacity());
        System.out.println("buf.maxCapacity() : " + buf.maxCapacity());
        System.out.println("buf.readerIndex() : " + buf.readerIndex());
        System.out.println("buf.writerIndex() : " + buf.writerIndex());
        System.out.println("buf.isReadable() : " + buf.isReadable());
        System.out.println("buf.isWritable() : " + buf.isWritable());
        System.out.println("buf.hashCode() : " + buf.hashCode());

        buf.writeInt(11);
        buf.retain();
        buf.release();
//        buf.release();
        buf.writeByte(21);
        buf.writeInt(22);
        buf.writeInt(33);
//        System.out.println(buf.readInt());
//        System.out.println(buf.readByte());
//        System.out.println(buf.readInt());
        byte[] bytes = new byte[buf.readableBytes() * 2];
        buf.readBytes(bytes, 0, buf.readableBytes());

        System.out.println("------ after operation ------");
        System.out.println("buf.capacity() : " + buf.capacity());
        System.out.println("buf.maxCapacity() : " + buf.maxCapacity());
        System.out.println("buf.readerIndex() : " + buf.readerIndex());
        System.out.println("buf.writerIndex() : " + buf.writerIndex());
        System.out.println("buf.isReadable() : " + buf.isReadable());
        System.out.println("buf.isWritable() : " + buf.isWritable());
        System.out.println("buf.hashCode() : " + buf.hashCode());

        ByteBuf buf2 = allocator.buffer();
        buf2.writeByte('c');
        buf2.writeByte('i');
        buf2.writeByte('w');
        buf2.writeByte('e');
        buf2.writeByte('i');
        System.out.println(buf2.toString(Charset.defaultCharset()));

    }

}
