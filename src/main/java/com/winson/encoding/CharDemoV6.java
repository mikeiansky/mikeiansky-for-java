package com.winson.encoding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author winson
 * @date 2021/8/11
 **/
public class CharDemoV6 {

    public static void main(String[] args) {

        String str = "\u4E2D";
        System.out.println(str.length());
        System.out.println("bytes length : " + str.getBytes().length);

        System.out.println("from file -----------> ");
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "b.txt";
//        System.out.println(path);



        try {
            FileInputStream in = new FileInputStream(new File(path));
            byte[] buf = new byte[1024];
            int readLength = in.read(buf);
            System.out.println(readLength);
            String result = new String(buf);
            System.out.println(result);

            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (byte b : hexStringToByteArray("4E2D")) {
            System.out.println(b);
        }

    }

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

}
