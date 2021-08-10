package com.winson.protocol.http;

import java.nio.charset.Charset;

/**
 * @author winson
 * @date 2021/8/10
 **/
public class CharsetDemoV3 {

    public static void main(String[] args) {

        String code = "中文";
        System.out.println(code);
        System.out.println(code.length());
        System.out.println(code.getBytes().length);
//        System.out.println(new String(code.getBytes(Charset.forName("gbk"))));

    }

}
