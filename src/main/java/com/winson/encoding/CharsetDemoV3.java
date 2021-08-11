package com.winson.encoding;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * @author winson
 * @date 2021/8/10
 **/
public class CharsetDemoV3 {

    public static void main(String[] args) {

        String code = "中文";
        // utf-8 -> unicode
        System.out.println(code);
        // 字符长度
        System.out.println(code.length());
        // unicode 转成 系统默认编码（utf-8) 字节数组长度
        System.out.println(code.getBytes().length);
        // unicode 转成 gbk字节数组
        byte[] buf = code.getBytes(Charset.forName("gbk"));
        // gbk字节数组长度
        System.out.println(buf.length);
        try {
            // 字节数组 使用系统默认编码（utf-8)转成 unicode 字符数组，会乱码
            System.out.println(new String(buf));
            // 字节数组 使用指定编码（gbk) 转成 unicode格式字符，不会乱码
            System.out.println(new String(buf,"gbk"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
