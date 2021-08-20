package com.winson.utils.encoding;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author winson
 * @date 2021/8/13
 **/
public class UrlEncodingDemoV1 {

    public static void main(String[] args) throws UnsupportedEncodingException {
        try {
            // 中 gbk 编码 D6D0
            String gbkSource = "%D6%D0";
            String result1 = URLDecoder.decode(gbkSource, "gbk");
            System.out.println(result1);
            // 中 utf-8 编码 E4B8AD
            String utf8Source = "%E4%B8%AD";
            String result2 = URLDecoder.decode(utf8Source, "utf-8");
            System.out.println(result2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(URLEncoder.encode("haha1", "utf-8"));
        System.out.println(URLEncoder.encode("haha2", "gbk"));
        System.out.println(URLEncoder.encode("haha11", "utf-8"));
        System.out.println(URLEncoder.encode("haha22", "gbk"));

    }

}
