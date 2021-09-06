package com.winson.framework.netty.rpc_v2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author winson
 * @date 2021/8/30
 **/
public class ByteBufDemo {

    public static void printInfo(ByteBuf buf, String separate) {
        System.out.println(" --------------> " + separate);
        System.out.println("isReadOnly : " + buf.isReadOnly());
        System.out.println("isReadable : " + buf.isReadable());
        System.out.println("readerIndex : " + buf.readerIndex());
        System.out.println("readableBytes : " + buf.readableBytes());
        System.out.println("isWritable : " + buf.isWritable());
        System.out.println("writerIndex : " + buf.writerIndex());
        System.out.println("writableBytes : " + buf.writableBytes());
        System.out.println("capacity : " + buf.capacity());
        System.out.println("maxCapacity : " + buf.maxCapacity());
    }

    public static void main(String[] args) {

        ByteBuf buf = ByteBufAllocator.DEFAULT.heapBuffer(8, 20);
        printInfo(buf, "init");
        buf.writeBytes(new byte[]{1, 2, 3, 4});
        printInfo(buf, "write 1234");
        buf.writeBytes(new byte[]{5, 6, 7, 8});
        printInfo(buf, "write 5678");
        buf.writeBytes(new byte[]{8, 9, 10, 11});
        printInfo(buf, "write 8-11");

        byte[] bs = new byte[4];
        buf.readBytes(bs);
        System.out.println("xxxxx");
        for (byte b : bs) {
            System.out.println(b);
        }
        printInfo(buf, "read bs");
        buf.getBytes(4, bs);
        for (byte b : bs) {
            System.out.println(b);
        }
        printInfo(buf, "read bs");
        buf.readBytes(8);
        printInfo(buf, "readBytes(8)");
//        buf.readBytes(1);
//        printInfo(buf, "readBytes(1)");
//        byte[] as = buf.array();
//        printInfo(buf, "buf.array()");
//        System.out.println("as length : " + as.length);
        System.out.println(buf.getByte(2));
        printInfo(buf, "buf.getByte(2)");

    }

}
