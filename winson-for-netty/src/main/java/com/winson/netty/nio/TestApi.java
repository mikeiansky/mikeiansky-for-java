package com.winson.netty.nio;

/**
 * @author winson
 * @date 2022/5/22
 **/
public class TestApi {

    public static void main(String[] args) {

        int value = 4;
        int temp1 = 1 | value;
        int temp2 = 1 | ~value;
        int temp3 = ~value;
        System.out.println(temp1);
        System.out.println(temp2);
        System.out.println(Integer.toBinaryString(value));
        System.out.println(Integer.toBinaryString(temp3));

    }
}
