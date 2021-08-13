package com.winson.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author winson
 * @date 2021/8/13
 **/
public class RegularDemoV3 {

    public static void main(String[] args) {

        final String regex = "((\\d{4})-(\\d{2})-(\\d{2})) ((\\d{2}):(\\d{2}):(\\d{2}))";
        final String string = "2020-05-10 20:23:05";
        final String subst = "日期 $1 时间 $2";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);
        matcher.find();
        System.out.println(matcher.group());
        System.out.println(matcher.group(1));
        System.out.println(matcher.group(2));
        System.out.println(matcher.group(3));
        System.out.println(matcher.group(4));

//        // The substituted value will be contained in the result variable
        final String result = matcher.replaceAll(subst);

        System.out.println("Substitution result: " + result);

    }

}
