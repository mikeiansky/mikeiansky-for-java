package com.winson.jdkapi.exception;

/**
 * @author mike ian
 * @date 2023/8/25
 * @desc
 **/
public class FinallyExceptionDemoV1 {



    public static void main(String[] args) {

        try {
            String hello = null;
            System.out.println(hello.length());
        } finally {
            System.out.println("run finally code");
        }

    }

}
