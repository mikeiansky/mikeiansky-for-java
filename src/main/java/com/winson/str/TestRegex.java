package com.winson.str;

/**
 * @author winson
 * @date 2021/3/29
 **/
public class TestRegex {

    public static void main(String[] args) {
//        String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0-9])|(18[0-9])|166|198|199|(147))\\d{8}$";
//        String mobile = "17128833682";
//        System.out.println(mobile.matches(regex));
//        userId : 1246206 , email : liumengya_i@didiglobal.com
//        userId : 1247349 , email : helojob@bytedance.com
//        userId : 1247385 , email : dengxia@sunlands.com
//        userId : 293426 , email : steephenliu@tencent.com
//        userId : 1428530 , email : @zichan360.com
//        userId : 1428553 , email : wangxiaoyu@w
//        userId : 1513465 , email : Lily.yin@hr_mp.com
//
//        String emailRegex = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
//        String email = "helojob@bytedance.com";
//        System.out.println(email.matches(emailRegex));

//        String before = "2020-01-05 03:23:22";
//        String after = "2020-01-04 03:55:24";
//        System.out.println(after.compareTo(null));

//        String source = "helloworld";
//        System.out.println(source);
//        System.out.println(source.substring(0, 5));

        String mobileReg = "1\\d{10}";
        String mobile = "22345678911";
        System.out.println(mobile.matches(mobileReg));

        String emailReg = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
        String email = "4515551511@qq.co";
        System.out.println(email.matches(emailReg));

    }

}
