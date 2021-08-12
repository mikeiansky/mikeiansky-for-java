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

    public static void main(String[] args) throws IOException {

        String source = "This is java file object\r\n\r\nChange your mind are you ok never say good bye!";
        byte[] buf = source.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(buf.length);
        System.out.println(byteBuffer.get());

    }

}
