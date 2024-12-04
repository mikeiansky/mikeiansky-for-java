package com.winson.utils.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mike ian
 * @date 2023/8/22
 * @desc
 **/
public class RegularDemoV9 {

    public static void main(String[] args) {

        String message = "is my first commit commit for git git message";

        String regex = "(\\w+) \\1";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);
        System.out.println(matcher.find());
        System.out.println(matcher.group(1));
        System.out.println(matcher.find());
        System.out.println(matcher.group(1));
        System.out.println(matcher.find());

    }

}
