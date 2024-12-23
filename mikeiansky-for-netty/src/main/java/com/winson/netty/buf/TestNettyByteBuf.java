package com.winson.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

import java.nio.charset.StandardCharsets;

/**
 * @author winson
 * @date 2022/5/22
 **/
public class TestNettyByteBuf {

    public static void main(String[] args) {

        PooledByteBufAllocator allocator = new PooledByteBufAllocator();
        ByteBuf byteBuf = allocator.heapBuffer(10, 22);
//        byteBuf.capacity(1);
//        System.out.println(byteBuf);
        System.out.println("byteBuf.capacity() : "+byteBuf.capacity());
        String str = "X01234";
        byte[] bts = str.getBytes(StandardCharsets.UTF_8);
//        byteBuf.writerIndex(8);
        byteBuf.writeBytes(bts);
//        byteBuf.writerIndex(1);
//        byteBuf.writerIndex(20);
        System.out.println("byteBuf.isWritable() : "+byteBuf.isWritable());
        byteBuf.writeBytes(bts);

        //
        byteBuf.writeFloat(1.0f);
        //

        System.out.println("byteBuf.isWritable() : "+byteBuf.isWritable());


//        byteBuf.writeByte(1);

        System.out.println("byteBuf.capacity() : "+byteBuf.capacity());
        System.out.println("byteBuf.writerIndex() : "+byteBuf.writerIndex());
        System.out.println("byteBuf.readerIndex() : "+byteBuf.readerIndex());
        System.out.println("byteBuf.readableBytes() : "+byteBuf.readableBytes());
        System.out.println((char) byteBuf.readByte());
        System.out.println((char) byteBuf.readByte());
        System.out.println(byteBuf.readBytes(2));
        System.out.println((char) byteBuf.readByte());
//        System.out.println((char) );
        byteBuf.readBytes(2);
//        byteBuf.discardReadBytes();
//        byteBuf.duplicate();
        byteBuf.release();
        System.out.println("byteBuf.readableBytes() : "+byteBuf.readableBytes());
//        System.out.println((char) byteBuf.readByte());
        System.out.println("byteBuf.readerIndex() : "+byteBuf.readerIndex());
        System.out.println("byteBuf.isWritable() : "+byteBuf.isWritable());
        System.out.println("byteBuf.isReadable() : "+byteBuf.isReadable());
//        byteBuf.readerIndex(0);

//        byteBuf.writeBytes(bts);

//        byte[] tbs = new byte[4];
//        ByteBuf tbf = byteBuf.readBytes(tbs);
//        byteBuf.readBytes(tbs, 0, tbs.length);
//        byteBuf.readInt();
//        byteBuf.readBytes(tbs);
//        System.out.println(new String(tbs));
//        ByteBuf sbf = byteBuf.slice(0,10);
//        System.out.println(byteBuf);
//        System.out.println(sbf);
//        sbf.release();
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
