package com.winson.utils.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mike ian
 * @date 2023/8/23
 * @desc
 **/
public class RegularDemoV10 {

    public static void main(String[] args) {

        String reg2 = "a*?";
        String text = "aaabb";

        Pattern pattern = Pattern.compile(reg2);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("'" + matcher.group() + "'");
        }

    }

}
