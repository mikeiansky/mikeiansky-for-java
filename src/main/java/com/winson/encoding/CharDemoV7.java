package com.winson.encoding;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author winson
 * @date 2021/8/11
 **/
public class CharDemoV7 {

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] b = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            b[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }
        return b;
    }

    public static void main(String[] args) throws FileNotFoundException {

//        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"c.txt";
//
//        FileInputStream in = new FileInputStream(path);
//        byte[] buf = new byte[1024 * 10];
//        try {
//            in.read(buf);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        System.out.println(new String(buf));

//        String str = "\\u662F";
//        System.out.println(str);
//        String reg1 = "(^\\\\u\\w{4})";
//        System.out.println(str.matches(reg1));

//        String source = "abcdefj123456ac1234bb567cc";
//        String reg2 = "\\d{4,}";
//        Pattern pattern = Pattern.compile(reg2);
//        Matcher matcher = pattern.matcher(source);
//        System.out.println("find result : " + matcher.find());
//        System.out.println("find result : " + matcher.group(0));
//        System.out.println("find result : " + matcher.find());
//        System.out.println("find result : " + matcher.group(0));
//        System.out.println("find result : " + matcher.find());
//        System.out.println("find result : " + matcher.group(0));

        List<Byte> bufs = new ArrayList<>();

        String source2 = "\\u662F\\u5426\\u5F00\\u542F\\u6587\\u6863\\uFF0C\\u5F00\\u53D1\\u6D4B\\u8BD5\\u73AF\\u5883\\u5F00\\u542F\\uFF0C\\u751F\\u4EA7\\u5173\\u95ED";
//        System.out.println(source2);
        String reg3 = "\\\\u[0-9a-fA-F]{4}";
        Pattern p2 = Pattern.compile(reg3);
        Matcher m3 = p2.matcher(source2);
        while (m3.find()){
            String result = m3.group();
//            System.out.println(result);
//            System.out.println();
            for (byte b : hexStringToByteArray(result.substring(2))) {
                bufs.add(b);
            }
        }
        byte[] buf = new byte[bufs.size()];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = bufs.get(i);
        }
        String result = new String(buf, StandardCharsets.UTF_16BE);
        System.out.println(result);

        String replaceSource = "add number 12 and 13 result = 25";

        Pattern p4 = Pattern.compile("\\d+");
        Matcher m5 = p4.matcher(replaceSource);
        String r2 = m5.replaceFirst("aa");
        System.out.println(r2);

    }

}
