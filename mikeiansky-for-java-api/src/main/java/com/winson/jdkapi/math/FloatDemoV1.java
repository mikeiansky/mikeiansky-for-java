package com.winson.jdkapi.math;

/**
 * @author winson
 * @date 2022/6/18
 **/
public class FloatDemoV1 {

    public static void main(String[] args) {

        Float f1 = -0.75f;
//        Float f1 = 4f;

        System.out.println(Integer.toBinaryString(Float.floatToIntBits(f1)));
        System.out.println(Integer.toBinaryString(Float.floatToRawIntBits(f1)));

    }

}
