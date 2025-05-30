package com.winson.netty.v2.buf;

import java.nio.ByteBuffer;

/**
 * @author mike ian
 * @date 2025/5/30
 * @desc
 **/
public class JavaBufferV2Demo {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println("buffer.capacity() : " + buffer.capacity());
        System.out.println("buffer.maxCapacity() : " + buffer.capacity());
        System.out.println("buffer.position() : " + buffer.position());
        System.out.println("buffer.limit() : " + buffer.limit());
        System.out.println("buffer.isReadOnly() : " + buffer.isReadOnly());
        System.out.println("buffer.isDirect() : " + buffer.isDirect());
        System.out.println("buffer.hasRemaining() : " + buffer.hasRemaining());

        System.out.println("------ after operation ------");
        buffer.putInt(11);
        buffer.putInt(22);
//        System.out.println("buffer.getInt() : " + buffer.getInt());
        buffer.flip();
        System.out.println("buffer.limit() : " + buffer.limit());
        System.out.println("buffer.getInt() : " + buffer.getInt());
        System.out.println("buffer.getInt() : " + buffer.getInt());

        System.out.println("after put and flip");
        buffer.position(buffer.limit());
        buffer.limit(buffer.limit() + 4);
        buffer.putInt(33);
        buffer.flip();
        System.out.println("buffer.getInt() : " + buffer.getInt());
        System.out.println("buffer.getInt() : " + buffer.getInt());
        System.out.println("buffer.getInt() : " + buffer.getInt());

        System.out.println("completed");

    }

}
