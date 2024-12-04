package com.winson.netty.buf;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * @author winson
 * @date 2022/5/22
 **/
public class TestByteBuffer {

    public static void main(String[] args) {
        System.out.println("TestByteBuffer ... ");
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
//        byteBuffer.limit(10);

        System.out.println("byteBuffer.limit() : " + byteBuffer.limit());
        byteBuffer.put("12345670".getBytes());
        System.out.println("byteBuffer.limit() : " + byteBuffer.limit());
        System.out.println("byteBuffer.position() : " + byteBuffer.position());
        System.out.println("byteBuffer.capacity() : " + byteBuffer.capacity());
        System.out.println(new String(byteBuffer.array()));
        System.out.println(byteBuffer.get(8));
        byteBuffer.flip();
//        byteBuffer.put(1, "cc".getBytes());
        byteBuffer.put("gf".getBytes(), 1 ,1);
//        byteBuffer.put(4,"xy".getBytes());
        byteBuffer.put(6, (byte) 'j');


        System.out.println("byteBuffer.limit() : " + byteBuffer.limit());
        System.out.println("byteBuffer.position() : " + byteBuffer.position());
        System.out.println("byteBuffer.capacity() : " + byteBuffer.capacity());
        System.out.println(new String(byteBuffer.array()));
        byteBuffer.position(3);
        byteBuffer.put("cd".getBytes());
        byteBuffer.put(1, (byte) 'x');
        byteBuffer.position(0);
//        byteBuffer.putInt(2);
//        byteBuffer.putInt(1);
//        byteBuffer.putChar(2, 'y');
        byteBuffer.mark();
//        byteBuffer.limit(30);
//        byteBuffer.rewind();
        byteBuffer.reset();
        // limit
        // position
        // mark
        // capacity
//        byteBuffer.duplicate();
        System.out.println(new String(byteBuffer.array()));
        System.out.println("byteBuffer.remaining() : " + byteBuffer.remaining());
        System.out.println("byteBuffer.limit() : " + byteBuffer.limit());
        System.out.println("byteBuffer.position() : " + byteBuffer.position());
        System.out.println("byteBuffer.capacity() : " + byteBuffer.capacity());
        System.out.println(new String(byteBuffer.array()));
//        System.out.println(byteBuffer.get(8));
//        System.out.println("2 : " + (int)('2'));
//        System.out.println("byteBuffer.get(1) : " + byteBuffer.get(0));
//        System.out.println("byteBuffer.position() : " + byteBuffer.position());
//        byteBuffer.position(0);
//        byte[] buf = new byte[3];
//        byteBuffer.get(3, buf);
//        byteBuffer.get(buf);
//        System.out.println("buf : " + new String(buf));

//        CharBuffer charBuffer = byteBuffer.asCharBuffer();
//        System.out.println("charBuffer.limit() : "+charBuffer.limit());
//        System.out.println("charBuffer.position() : "+charBuffer.position());
//        System.out.println("charBuffer.capacity() : "+charBuffer.capacity());
//        System.out.println(charBuffer.get());
//        System.out.println(charBuffer.get());
//        System.out.println(charBuffer.get());
//        System.out.println(charBuffer.get());

//        byteBuffer.get(buf, 1, 1);
//        byteBuffer.get(1, buf, 1, 1);

//        System.out.println(byteBuffer.get(1));
//        System.out.println(byteBuffer.get(1));
//        System.out.println(byteBuffer.get(1));

//        byteBuffer.put("2".getBytes());

    }

}
