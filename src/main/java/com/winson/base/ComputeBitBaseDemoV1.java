package com.winson.base;

/**
 * @author winson
 * @date 2021/8/23
 **/
public class ComputeBitBaseDemoV1 {

    public static void main(String[] args) {

        int a = 1;
        int b = -1;
//        System.out.println(Integer.toBinaryString(a));
//        System.out.println(Integer.toBinaryString(b));
//        System.out.println(12 % 7);
//        System.out.println(Math.floor(12 / 7f));
//        System.out.println(-12 % 7);
//        System.out.println(-2 % 12);
//        System.out.println(12 % -7);
//        Float.parseFloat("0.2f");
        float f1 = 100_000_003_000f;
        float f2 = 200_000_000_001f;
        System.out.println(f1 + f2);

        float f3 = 4294967244f;
        float f4 = 4294967295f;
//        System.out.println(f3);
//        System.out.println(f4);
        // 40 C0 00 00
//        byte b1 = 0x40;
//        byte b2 = (byte) 0xC0;
//        byte b3 = 0x00;
//        byte b4 = 0x00;
//        System.out.println(Integer.toBinaryString(b1));
//        System.out.println(Integer.toBinaryString(b2));
//        System.out.println(Integer.toBinaryString(b3));
//        System.out.println(Integer.toBinaryString(b4));
//
//        int c = 6;
//        System.out.println(Integer.toBinaryString(6));
//        String hex1 = "00000000000000000000000000000110";
//        String hex2 = "01000000110000000000000000000000";
//        System.out.println(hex1);
//        System.out.println(hex2);
//
//        // 3.4028f
//        // 3.4028f
//        float ff1 = 123456780;
//        System.out.println(ff1);
//
//        double d1 = 12345678991021340147.1;
//        System.out.println(d1);

        System.out.println(Float.NaN);

        //  7F C0 00 00
        byte bb1 = (byte) 0xf7;
        byte bb2 = (byte) 0xc0;
        System.out.println(Integer.toBinaryString(bb1));
        System.out.println(Integer.toBinaryString(bb2));

        double d = Double.NaN;

        // 11110111
        // 11000000
        // 11110111 11000000

        float ff1 = 123456780f;
        float ff2 = 123456782f;
        System.out.println(ff1);
        System.out.println(ff2);
        System.out.println(ff2 == ff1);


    }

}
