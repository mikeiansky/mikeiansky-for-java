package com.winson.utils.encoding;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author winson
 * @date 2021/8/13
 **/
public class CharDemoV11 {

    public static void main(String[] args) {

        String source = "{\r\n" +
                "    \"userName\": \"lyf--1\",\r\n" +
                "    \"contact\": \"18025855689\",\r\n" +
                "    \"zn-name\": \"中\",\r\n" +
                "    \"wechat\": \"123456\"\r\n" +
                "}";

        System.out.println(source.length());
        System.out.println(source.getBytes(Charset.forName("utf-8")).length);

    }

}
