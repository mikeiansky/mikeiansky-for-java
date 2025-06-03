package com.winson.netty.v2.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;

/**
 * @author mike ian
 * @date 2025/5/30
 * @desc
 **/
public class NettyBufV2Demo {

    public static void main(String[] args) {

        PooledByteBufAllocator allocator = new PooledByteBufAllocator(true);
        ByteBuf buf = allocator.heapBuffer(8, 16);
        System.out.println("------ before operation ------");
        System.out.println("buf.capacity() : " + buf.capacity());
        System.out.println("buf.maxCapacity() : " + buf.maxCapacity());
        System.out.println("buf.readerIndex() : " + buf.readerIndex());
        System.out.println("buf.writerIndex() : " + buf.writerIndex());
        System.out.println("buf.isReadable() : " + buf.isReadable());
        System.out.println("buf.isWritable() : " + buf.isWritable());
        System.out.println("buf.hashCode() : " + buf.hashCode());

        buf.writeInt(11);
        buf.writeByte(21);
        buf.writeInt(22);
        buf.writeInt(33);
        System.out.println(buf.readInt());
        System.out.println(buf.readByte());
        System.out.println(buf.readInt());
        System.out.println(buf.getInt(0));
        System.out.println(buf.getInt(5));
        System.out.println(buf.getByte(4));



        System.out.println("------ after operation ------");
        System.out.println("buf.capacity() : " + buf.capacity());
        System.out.println("buf.maxCapacity() : " + buf.maxCapacity());
        System.out.println("buf.readerIndex() : " + buf.readerIndex());
        System.out.println("buf.writerIndex() : " + buf.writerIndex());
        System.out.println("buf.isReadable() : " + buf.isReadable());
        System.out.println("buf.isWritable() : " + buf.isWritable());
        System.out.println("buf.hashCode() : " + buf.hashCode());

    }

}
