package com.winson.utils.encoding;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author winson
 * @date 2021/8/12
 **/
public class CharDemoV8 {

    public static void main(String[] args) {
        // 0xD6D0;
        // 中
        // CEC4
        // 文
        byte[] buf = new byte[]{(byte) 0xd6, (byte) 0xd0, (byte) 0xce, (byte) 0xc4};
        String source = new String(buf, Charset.forName("gbk"));
        System.out.println(source);

        for (byte b : source.getBytes(StandardCharsets.UTF_16BE)) {
            System.out.println(Integer.toHexString(b));
        }

    }

}
