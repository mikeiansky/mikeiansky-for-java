package com.winson.app001;

import com.winson.app002.Hello002;
//import com.winson.app003.Hello003;

/**
 * @author mike ian
 * @date 2023/6/2
 * @desc
 **/
public class Hello001 {

    public static void hello(String msg){
        System.out.println("hello world 001 : " + msg);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("hello world 001");

        hello("ciwei");

        Hello002.hello("ciwei");
//        Hello003.hello("ciwei");
//        Hello003.hello("ciwei");

    }

}
