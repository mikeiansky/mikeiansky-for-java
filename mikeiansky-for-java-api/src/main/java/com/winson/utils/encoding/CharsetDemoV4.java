package com.winson.utils.encoding;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * @author winson
 * @date 2021/8/10
 **/
public class CharsetDemoV4 {

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println("Charset.defaultCharset().name():" + Charset.defaultCharset().name());

//        String code = "中";
        String code = "中";

        byte[] buf = code.getBytes();
        for (byte b : buf) {
            System.out.println(Integer.toHexString(b));
        }

        String encode = URLEncoder.encode(code, "utf-8");
        System.out.println("code:" + code);

    }

}
