package com.winson.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author winson
 * @date 2022/5/22
 **/
public class TestByteBuf {

    public static void main(String[] args) {

        PooledByteBufAllocator allocator = new PooledByteBufAllocator();
        ByteBuf byteBuf = allocator.heapBuffer(10, 22);
//        byteBuf.capacity(1);
//        System.out.println(byteBuf);
        System.out.println("byteBuf.capacity() : "+byteBuf.capacity());
        String str = "0123456789x";
        byte[] bts = str.getBytes(StandardCharsets.UTF_8);
//        byteBuf.writerIndex(8);
        byteBuf.writeBytes(bts);
//        byteBuf.writerIndex(1);
//        byteBuf.writerIndex(20);
        System.out.println("byteBuf.isWritable() : "+byteBuf.isWritable());
        byteBuf.writeBytes(bts);
        System.out.println("byteBuf.isWritable() : "+byteBuf.isWritable());


//        byteBuf.writeByte(1);

        System.out.println("byteBuf.capacity() : "+byteBuf.capacity());
        System.out.println("byteBuf.writerIndex() : "+byteBuf.writerIndex());
        System.out.println("byteBuf.readerIndex() : "+byteBuf.readerIndex());
        System.out.println("byteBuf.readableBytes() : "+byteBuf.readableBytes());
        System.out.println((char) byteBuf.readByte());
//        System.out.println((char) );
        byteBuf.readBytes(2);
//        byteBuf.discardReadBytes();
//        byteBuf.duplicate();
        System.out.println("byteBuf.readableBytes() : "+byteBuf.readableBytes());
        System.out.println((char) byteBuf.readByte());
        System.out.println("byteBuf.readerIndex() : "+byteBuf.readerIndex());
        System.out.println("byteBuf.isWritable() : "+byteBuf.isWritable());
//        byteBuf.readerIndex(0);

        byte[] tbs = new byte[4];
//        ByteBuf tbf = byteBuf.readBytes(tbs);
//        byteBuf.readBytes(tbs, 0, tbs.length);
//        byteBuf.readInt();
        byteBuf.readBytes(tbs);
        System.out.println(new String(tbs));
//        System.out.println(tbf.array().length);

//        System.out.println(str.substring(0, 1));
//        System.out.println(str.substring(1, 3));
//        int[] i1 = {0,1,2,3,4,5,6};
//        int[] i2 = new int[3];
//        System.arraycopy(i1, 4, i2, 0, 3);
//        System.out.println(Arrays.stream(i2).boxed().collect(Collectors.toList()));
//        System.out.println(new String(byteBuf.array(),0, 11));
//        System.out.println(byteBuf.array().length);

    }

}
