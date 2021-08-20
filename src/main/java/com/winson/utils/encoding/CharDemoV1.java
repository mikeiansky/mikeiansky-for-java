package com.winson.utils.encoding;

import java.nio.charset.StandardCharsets;

/**
 * @author winson
 * @date 2021/8/11
 **/
public class CharDemoV1 {

    public static void main(String[] args) {

        // 中文生僻字字符编码范围
        // https://www.qqxiuzi.cn/zh/hanzi-unicode-bianma.php

        char a = 'a';
        char b = '中';
        char c = '圚';
        char d = '壆';
        char e = 'ﻻ';
        char g = '㇀';
        char h = '⿰';
        char i = 'ㄅ';
        char j = 'ㆠ';

//        char f = '𠀀'; // 编译失败：异常 too many characters in character literal

        System.out.println("char a = " + a);
        System.out.println("char b = " + b);
        System.out.println("char c = " + c);
        System.out.println("char d = " + d);
        System.out.println("char e = " + e);
//        System.out.println("char f = " + f);
        System.out.println("char g = " + g);
        System.out.println("char h = " + h);
        System.out.println("char i = " + i);
        System.out.println("char j = " + j);

        byte[] buf = new byte[]{
                // 1110 1111
                (byte) 0xef,
                // 1010 1010
                (byte) 0xbb,
                // 1010 1010
                (byte) 0xbb
        };

        System.out.println(new String(buf));

        String ss = "中";
        System.out.println("中 length : " + ss.length());
        // 中文 𠀀 无法直接用一个字符显示
        // unicode \uD840\uDC00
        String s = "𠀀";
        System.out.println("𠀀 length : " + s.length());
        for (byte bt : s.getBytes()) {
            System.out.println(Integer.toBinaryString(bt));
        }
        System.out.println(s.getBytes().length);
        System.out.println(s);
        System.out.println("-------------");
        String zs = "\u4E2D";
        System.out.println("zs : " + zs);
        String sf = "\uD840";
        System.out.println("sf : " + sf);
        String sp = "\uDC00";
        System.out.println("sp : " + sp);


    }

}
