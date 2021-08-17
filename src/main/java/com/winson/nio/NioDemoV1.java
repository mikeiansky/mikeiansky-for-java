package com.winson.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author winson
 * @date 2021/8/12
 **/
public class NioDemoV1 {

    private static void printlnByteBufferInfo(String tag, ByteBuffer buf) {
        System.out.println(" --------------- " + tag + " -------------- ");
        System.out.println(tag + " capacity : " + buf.capacity());
        System.out.println(tag + " limit : " + buf.limit());
        System.out.println(tag + " position : " + buf.position());
//        System.out.println(tag + " arrayOffset : " + buf.arrayOffset());
    }

    public static void main(String[] args) throws IOException {

        String source = "Thisis java?+*. file object\r\n\r\nChange your mind are you ok never say good bye!";
        byte[] buf = source.getBytes();
        System.out.println("source length : " + source.length());
        System.out.println("source byte length : " + source.getBytes().length);

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        printlnByteBufferInfo("byteBuffer exec1 ", byteBuffer);
        byteBuffer.put(buf[0]);
        byteBuffer.put(buf[1]);
        byteBuffer.put(buf[2]);
        byteBuffer.put(buf[3]);
        byteBuffer.put(buf[4]);
        System.out.println(new String(byteBuffer.array()));
        printlnByteBufferInfo("byteBuffer exec2 ", byteBuffer);
        byteBuffer.put(buf[5]);
        System.out.println(byteBuffer.get(0));
        System.out.println(byteBuffer.get(1));
        printlnByteBufferInfo("byteBuffer exec3 ", byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        byteBuffer.flip();
        byteBuffer.limit(8);
        byteBuffer.position(5);
        printlnByteBufferInfo("byteBuffer exec3 ", byteBuffer);
        byteBuffer.position(4);
        byteBuffer.mark();
        byteBuffer.put(buf[4]);
        byteBuffer.put(buf[4]);
        printlnByteBufferInfo("byteBuffer exec4 ", byteBuffer);
//        byteBuffer.reset();
        printlnByteBufferInfo("byteBuffer exec5 ", byteBuffer);
        byteBuffer.rewind();
        printlnByteBufferInfo("byteBuffer exec6 ", byteBuffer);
        byteBuffer.flip();
        printlnByteBufferInfo("byteBuffer exec7 ", byteBuffer);
//        byteBuffer.flip();
//        System.out.println(byteBuffer.get(2));

        byteBuffer.flip();
//        byteBuffer.limit(10);
        byteBuffer.clear();
        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) 2);
        byteBuffer.put((byte) 3);
        byteBuffer.put((byte) 4);
        byteBuffer.put((byte) 5);
        byteBuffer.put((byte) 6);

        System.out.println(byteBuffer.get(0));
        printlnByteBufferInfo("byteBuffer exec7 ", byteBuffer);
        byteBuffer.clear();


        // 测试java中字节的处理
        int total = 10128;
        System.out.println(Integer.toBinaryString(total));
        // 0010011110010000
        byte b1 = (byte) 0x27;
        byte b0 = (byte) 0x90;
        int a = (b1 << 8) | (b0 & 0xff);
        System.out.println(a);

    }

}
