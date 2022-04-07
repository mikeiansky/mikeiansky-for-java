package com.winson.exceptions;

/**
 * @author winson
 * @date 2022/4/6
 **/
public class ExceptionDemo {

    public static void f1() {
        f2();
    }

    public static void f2() {
        f3();
    }

    public static void f3(){
        String a = "1";
        String b = null;
        String c = "null";
        System.out.println(a.length());
        System.out.println(b.length());
        System.out.println(c.length());
    }

    public static void main(String[] args) {
        try {
//            String a = "1";
//            String b = null;
//            String c = "null";
//            System.out.println(a.length());
//            System.out.println(b.length());
//            System.out.println(c.length());
            f1();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("------");
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getStackTrace().length);
            StackTraceElement stackTraceElement = e.getStackTrace()[0];
            System.out.println("stackTraceElement : " + stackTraceElement);
            System.out.println("stackTraceElement.getMethodName() : " + stackTraceElement.getMethodName());
            System.out.println("stackTraceElement.getClassName() : " + stackTraceElement.getClassName());
        }


    }

}
