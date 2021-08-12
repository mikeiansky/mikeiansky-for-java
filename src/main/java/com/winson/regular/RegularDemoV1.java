package com.winson.regular;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author winson
 * @date 2021/8/12
 **/
public class RegularDemoV1 {

    public static void main(String[] args) {

        System.out.println(RegularDemoV1.class.getPackage());

        String source = "changeabc123def456helloabcworld\n\rbbb";

        String reg = "((abc)[0-9]*)";

        Pattern pattern = Pattern.compile(reg);

        Matcher matcher = pattern.matcher(source);
        boolean findResult = matcher.find();
        System.out.println(findResult);
        System.out.println(matcher.group());
//        String replaceSource = matcher.replaceFirst("--");
//        System.out.println(replaceSource);

        findResult = matcher.find();
        System.out.println(findResult);
        System.out.println(matcher.group());

//        String str = "\uD02D";
//        System.out.println(str);

//        String u16 = "a";
//        for (byte aByte : u16.getBytes(StandardCharsets.UTF_16BE)) {
//            System.out.println(Integer.toHexString(aByte));
//        }
//        System.out.println(u16.getBytes(StandardCharsets.UTF_16BE).length);

    }

}
