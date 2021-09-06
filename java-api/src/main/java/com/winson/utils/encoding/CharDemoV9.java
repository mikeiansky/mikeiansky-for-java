package com.winson.utils.encoding;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author winson
 * @date 2021/8/12
 **/
public class CharDemoV9 {

    public static void main(String[] args) {

        String source = "中文";
        System.out.println(source);

        String gbk = new String(source.getBytes(StandardCharsets.UTF_8), Charset.forName("gbk"));
        System.out.println(gbk);

        for (byte b : gbk.getBytes(StandardCharsets.UTF_16BE)) {
            System.out.println(Integer.toHexString(b));
        }
        System.out.println(" ----------- ");
        String white = " ";
        System.out.println(white.length());
        System.out.println(white.getBytes(StandardCharsets.UTF_16BE).length);
        for (byte b : white.getBytes(StandardCharsets.UTF_16BE)) {
            System.out.println(Integer.toHexString(b));
        }

    }

}
