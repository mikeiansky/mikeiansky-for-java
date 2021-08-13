package com.winson.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author winson
 * @date 2021/8/13
 **/
public class RegularDemoV5 {

    public static void main(String[] args) {

        String source = "Cat";
        // 不区分大小写
        String reg = "(?i)cat";
        Pattern pattern = Pattern.compile(reg);

        Matcher matcher = pattern.matcher(source);
        System.out.println(matcher.matches());


    }

}
