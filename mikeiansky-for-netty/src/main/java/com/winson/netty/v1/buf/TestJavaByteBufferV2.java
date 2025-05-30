package com.winson.netty.v1.buf;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author winson
 * @date 2022/5/23
 **/
public class TestJavaByteBufferV2 {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put("1234".getBytes(StandardCharsets.UTF_8));
//        buffer.flip();
        System.out.println("buffer.limit() : " + buffer.limit());
        System.out.println("buffer.position() : " + buffer.position());
        System.out.println("buffer.capacity() : " + buffer.capacity());
        buffer.flip();
        System.out.println("buffer.limit() : " + buffer.limit());
        System.out.println("buffer.position() : " + buffer.position());
        System.out.println("buffer.capacity() : " + buffer.capacity());
//        buffer.limit(1024);
//        buffer.put("12345".getBytes(StandardCharsets.UTF_8));

    }

}
