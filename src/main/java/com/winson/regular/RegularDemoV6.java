package com.winson.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author winson
 * @date 2021/8/13
 **/
public class RegularDemoV6 {

    public static void main(String[] args) {

        String source = "cat it hte hat";

        String reg = "^cat|hat$";

        Pattern pattern = Pattern.compile(reg);

        Matcher matcher = pattern.matcher(source);
        System.out.println(matcher.matches());
        System.out.println("---------");
        System.out.println(matcher.find());
        System.out.println(matcher.group());
        System.out.println(matcher.find());
        System.out.println(matcher.group());

    }

}
