package com.winson.springboot.demo;

import org.springframework.stereotype.Service;

/**
 * @author winson
 * @date 2022/3/1
 **/
@Service
//@MyAnnotation
public class MyService {

    public void function1() {
        function2();
    }

    public void function2() {
        function3();
    }

    public void function3() {
        String a = "a";
        String b = null;
        String c = "c";
        System.out.println(a.length());
        boolean isTrue = true;
        if(isTrue){
            throw new IllegalArgumentException("test");
        } else {
            System.out.println(b.length());
        }
        System.out.println(c.length());
    }

    public void function() {
        function1();
    }

}
