package com.winson.str;

/**
 * @author winson
 * @date 2021/3/29
 **/
public class TestRegex {

    public static void main(String[] args) {
        String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0-9])|(18[0-9])|166|198|199|(147))\\d{8}$";
        String mobile = "17128833682";
        System.out.println(mobile.matches(regex));
    }

}
