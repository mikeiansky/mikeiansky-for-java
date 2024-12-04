package com.winson.utils.regular;

import java.util.regex.Pattern;

/**
 * @author winson
 * @date 2022/5/6
 **/
public class StringRegDemoV1 {

    public static void main(String[] args) {

        String str = "com.ciwei.rsm.thriftApi";

        String pattern = "com.ciwei..*.thriftApi";

        Pattern pt = Pattern.compile(pattern);
        System.out.println(pt.matcher(str).matches());

    }

}
