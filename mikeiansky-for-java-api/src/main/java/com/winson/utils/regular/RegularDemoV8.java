package com.winson.utils.regular;

import java.util.regex.Pattern;

/**
 * @author winson
 * @date 2022/4/15
 **/
public class RegularDemoV8 {

    public static void main(String[] args) {

        String patternStr = "[0,1,2]";
        Pattern pattern = Pattern.compile(patternStr);
        System.out.println(pattern.matcher("3").matches());

    }

}
