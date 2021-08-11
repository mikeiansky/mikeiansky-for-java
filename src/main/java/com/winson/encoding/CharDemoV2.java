package com.winson.encoding;

import java.nio.charset.StandardCharsets;

/**
 * @author winson
 * @date 2021/8/11
 **/
public class CharDemoV2 {

    public static void main(String[] args) {

        System.out.println("");
        // utf-16 \uD840\uDC00
        // unicode 00020000
        // bin : 0010 00000000 00000000
        String s = "𠀀";
        char[] sc = s.toCharArray();
        System.out.println("𠀀 length : " + s.length());

        byte[] sb = s.getBytes();
        System.out.println(sb.length);
//        for (byte b : sb) {
//            System.out.println(Integer.toHexString(b));
//        }
        System.out.println("---------- sc start -------------- ");
        System.out.println("sc length : " + sc.length);
        for (char c : sc) {
            System.out.println(Integer.toHexString(c));
        }
        System.out.println("---------- sc end   -------------- ");

        String br = new String(sb);
        System.out.println("br : " + br);

        char[] uc = new char[]{'\uD840', '\uDC00'};
        String ur = new String(uc);
        System.out.println("ur : " + ur);

    }

}
