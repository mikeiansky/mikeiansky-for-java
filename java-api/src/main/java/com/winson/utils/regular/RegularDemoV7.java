package com.winson.utils.regular;

/**
 * @author winson
 * @date 2021/8/13
 **/
public class RegularDemoV7 {

    public static void main(String[] args) {

        String reg = ".+";
        String source = " 我们 是 t";
        System.out.println(source.matches(reg));

    }

}
