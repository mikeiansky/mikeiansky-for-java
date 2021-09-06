package com.winson.utils.encoding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author winson
 * @date 2021/8/13
 **/
public class CharDemoV12 {

    public static void main(String[] args) {

        byte[] buf1 = new byte[10];
        buf1[0] = (byte) 0xE4;
        buf1[1] = (byte) 0xB8;
        buf1[2] = (byte) 0xAD;
//        buf1[3] = (byte) 0xAD;
//        buf1[3] = (byte) 0x20;
        buf1[4] = (byte) 0xE4;
        buf1[5] = (byte) 0xB8;
        buf1[6] = (byte) 0xAD;
        String s1 = new String(buf1);
        System.out.println("'" + s1 + "'");

        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(s1);
        System.out.println(matcher.find());

        String s3 = "中 中   ";
        System.out.println("'"+s3 +"'");
        Pattern pattern2 = Pattern.compile("\\s");
        Matcher matcher2 = pattern2.matcher(s3);
        System.out.println(matcher2.find());

        byte[] buf = new byte[1024];
        buf[0] = (byte) 0xE4;
        buf[1] = (byte) 0xB8;
        buf[2] = (byte) 0xAD;
        String s2 = new String(buf);
        System.out.println("'" + s2 + "'");
        System.out.println("'" + s2.trim()+"'");

        int size = 1_000_000;

        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            String source = new String(buf);
        }
        long end = System.currentTimeMillis();
//        System.out.println(source);
        System.out.println("use time : " + (end - start));
    }

}
