package com.winson.utils.resource;

import java.util.Locale;

/**
 * @author winson
 * @date 2021/6/29
 **/
public class ResourceDemo {

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        System.out.println("user.dir = " + userDir);

        String user = System.getProperty("user");
        System.out.println("user = " + user);

        System.out.println(Locale.getDefault());
        System.out.println(Locale.getAvailableLocales().length);
//        for (Locale locale : Locale.getAvailableLocales()) {
//            System.out.println(locale.getCountry());
//        }
    }

}
