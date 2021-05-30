package com.winson.str;

import java.io.UnsupportedEncodingException;

/**
 * @author winson
 * @date 2021/5/10
 **/
public class TestEncode {

    public static void main(String[] args) throws UnsupportedEncodingException {
//        String a = "a";
//        String w = "文";
//        System.out.println(a + " : " + printStrBit(a));
//        System.out.println(w + " : " + printStrBit(w));
//
//        String mobile = "13537885242";
//        System.out.println(mobile.substring(7, 11));

        String str = "a";
        String str2 = "文";
        System.out.println(str.getBytes().length);
        System.out.println(str2.getBytes("utf-8").length);
        System.out.println(str2.getBytes("gbk").length);

    }

    public static String printByteBit(byte b){
        return String.format("%8s",Integer.toBinaryString(b)).replaceAll(" ", "0");
    }

    public static String printStrBit(String result){
        byte[] bytes = result.getBytes();
        StringBuffer sb = new StringBuffer();
        for (byte b : bytes) {
            String bs = Integer.toBinaryString(b);
            if(bs.length() > 8 ){
                sb.append(bs.substring(bs.length()-8));
            }else{
                sb.append(String.format("%8s",bs).replaceAll(" ", "0"));
            }
            sb.append(" ");
        }
        return sb.toString();
    }

}
