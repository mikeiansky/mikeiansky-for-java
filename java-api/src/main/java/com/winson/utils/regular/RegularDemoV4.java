package com.winson.utils.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author winson
 * @date 2021/8/13
 **/
public class RegularDemoV4 {

    public static void main(String[] args) {

        String source = "2020-10-11 2223-12-33";

        String reg = "(\\d{4})-(\\d{2})";

        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(source);
        matcher.find();
        System.out.println(matcher.groupCount());
        System.out.println(matcher.group());
        matcher.find();
        System.out.println(matcher.groupCount());
        System.out.println(matcher.group());

    }

}
