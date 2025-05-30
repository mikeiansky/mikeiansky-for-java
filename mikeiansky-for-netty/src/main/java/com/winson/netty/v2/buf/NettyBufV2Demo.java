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
        System.out.println("buf.capacity() : " + buf.capacity());
        System.out.println("buf.maxCapacity() : " + buf.maxCapacity());
        System.out.println("buf.readerIndex() : " + buf.readerIndex());
        System.out.println("buf.writerIndex() : " + buf.writerIndex());
        System.out.println("buf.isReadable() : " + buf.isReadable());
        System.out.println("buf.isWritable() : " + buf.isWritable());
//        buf.touch();

//        buf.writeBytes(new byte[]{1, 2, 3});
//        buf.writeBytes(new byte[]{4, 5, 6});
//        buf.writeBytes(new byte[]{7, 8, 9});
//        buf.writeBytes(new byte[]{10, 11, 12});
//        buf.writeBytes(new byte[]{13, 14, 15});
        buf.writeInt(1);
        buf.writeInt(2);
        buf.writeInt(3);
        buf.writeInt(4);
//        buf.writeInt(5);

//        buf.writeBytes(new byte[]{16, 17, 18});

//        buf.readerIndex(3);
//        System.out.println(buf.getInt(3));

        System.out.println(buf.readBytes(1).getByte(0));
        System.out.println(buf.readBytes(1).getByte(0));
        System.out.println(buf.readBytes(1).getByte(0));
        System.out.println(buf.readBytes(1).getByte(0));



        System.out.println("------ after operation ------");
        System.out.println("buf.capacity() : " + buf.capacity());
        System.out.println("buf.maxCapacity() : " + buf.maxCapacity());
        System.out.println("buf.readerIndex() : " + buf.readerIndex());
        System.out.println("buf.writerIndex() : " + buf.writerIndex());
        System.out.println("buf.isReadable() : " + buf.isReadable());
        System.out.println("buf.isWritable() : " + buf.isWritable());
        System.out.println("buf.isWritable() : " + buf.hashCode());

    }

}
