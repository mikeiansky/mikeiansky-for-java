package com.winson.encoding;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * @author winson
 * @date 2021/8/11
 **/
public class CharDemoV4 {

    public static void main(String[] args) {
        String str = "a";
        System.out.println(str);

//        byte[] bgkByte = str.getBytes(Charset.forName("gbk"));
//        for (byte b : bgkByte) {
//            System.out.println(b);
//        }

        // 字符'中'
        // gbk 编码：D6D0
        // utf-16be编码 ： \u4E2D

//        System.out.println(Charset.defaultCharset());

        System.out.println(Charset.defaultCharset());
        // gbk 编码
        byte[] buf = new byte[]{(byte) 0xD6, (byte) 0xD0};
        String gbk = new String(buf, Charset.forName("gbk"));
        System.out.println(gbk);
        System.out.println(gbk);

        System.out.println("main end");

    }

}
