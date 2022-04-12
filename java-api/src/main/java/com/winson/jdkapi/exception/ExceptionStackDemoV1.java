package com.winson.jdkapi.exception;

/**
 * @author winson
 * @date 2022/4/12
 **/
public class ExceptionStackDemoV1 {

    public static void methodOne() {
        try {
            System.out.println("methodOne ---- 1");
            methodTwo();
            System.out.println("methodOne ---- 2");
            System.out.println("methodOne ---- 3");
            System.out.println("methodOne ---- 4");
        } catch (Exception e) {
            throw new IllegalStateException("from methodOne", e);
        }

    }

    public static void methodTwo() {
        methodThree();
    }

    public static void methodThree() {
        methodFour();
    }

    public static void methodFour() {
        //        try {
        System.out.println("methodFour ***** 1");
        System.out.println("methodFour ***** 2");
        System.out.println("methodFour ***** 3");
        methodFive();
        System.out.println("methodFour ***** 4");
//        } catch (Exception e) {
//            throw new IllegalStateException("from methodFour", e);
//        }
    }

    public static void methodFive() {
        methodSix();
    }

    public static void methodSix() {
        try {
            System.out.println("methodSix +++++ 1");
            System.out.println("methodSix +++++ 2");
            if (1 == 1) {
                throw new IllegalStateException("hello exception");
            }
            System.out.println("methodSix +++++ 3");
            System.out.println("methodSix +++++ 4");
        } catch (Exception e) {
            throw new IllegalStateException("from methodSix", e);
        }

    }

    public static void main(String[] args) {
        methodOne();
    }

}
