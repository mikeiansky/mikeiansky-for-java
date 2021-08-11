package com.winson.encoding;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author winson
 * @date 2021/8/10
 * https://www.cnblogs.com/fuyoucaoyu/articles/5707911.html
 **/
public class CharsetDemoV1 {

    public static void main(String[] args) {

//        try {
//            FileInputStream in = new FileInputStream("");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        String word = "hello";
        System.out.println("------" + word + "-------");
        System.out.println(word.length());
        System.out.println(word.getBytes().length);
        String cw = "中";
        System.out.println("------" + cw + "-------");
        System.out.println(cw.length());
        System.out.println(cw.getBytes().length);
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "a.txt";
            System.out.println("path : " + path);
            FileInputStream in = new FileInputStream(path);
            byte[] buf = new byte[1024];
            int length = in.read(buf);
            System.out.println("------- file ---------");
            for (int i = 0; i < length; i++) {
                System.out.println(Integer.toBinaryString(buf[i]));
            }
            // 11100100;
            // 10111000;
            // 10101101;
            byte[] zb = new byte[]{(byte) 0xe4, (byte) 0xb8, (byte) 0xad};

            // 0111 1101
            // 11000001 10111101
            buf[0] = (byte) 0xc1;
            buf[1] = (byte) 0xbD;
//            buf[0] = (byte) 0x7d;
//            buf[0] = (byte) 0x8f;
//            buf[1] = (byte) 0x8f;
//            buf[0] = (byte) (buf[0] | 0x40);
//            System.out.println(Integer.toBinaryString(buf[0]));
//            buf[1] = (byte) (buf[1] | 0x10);
            System.out.println("default charset : " + Charset.defaultCharset().name());
//            String fs = new String(buf, 0, length, Charset.forName("gbk"));
            String fs = new String(buf, 0, length );
            System.out.println("raw length : "+length);
            System.out.println(fs);
            System.out.println(fs.length());
            System.out.println(fs.getBytes().length);
            System.out.println(new String(zb,0,zb.length));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
