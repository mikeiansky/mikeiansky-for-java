package com.winson.encoding;

/**
 * @author winson
 * @date 2021/8/11
 **/
public class CharDemoV3 {

    public static void main(String[] args) {
        // java char 使用unicode 16 位 两个字节来表示字符
        // '中' 的 utf-16be \u4E2D
        char a = 'a';
        System.out.println(Integer.toHexString(a));

    }

}
