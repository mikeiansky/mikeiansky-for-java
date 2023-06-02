package com.winson.app002;

import com.winson.app003.Hello003;

/**
 * @author mike ian
 * @date 2023/6/2
 * @desc
 **/
public class Hello002 {

    public static void hello(String msg) throws Exception {
        System.out.println("hello world 002 : " + msg);
        Hello003.hello(msg);

//        Class clazz = Class.forName("com.winson.app003.Hello003");
//        clazz.getMethod("hello", String.class).invoke(null, msg);
    }

    public static void main(String[] args) throws Exception {

//        System.out.println("hello world 002");
//        Hello003.hello("hello world 003");
        hello("ciwei");
    }

}
