package com.winson.jdkapi.exception;

/**
 * @author winson
 * @date 2022/4/12
 **/
public class ExceptionStackDemoV1 {

    public static void topOne() {
        topTwo();
    }

    public static void topTwo() {
        methodOne();
    }

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
//        try {
        System.out.println("methodSix +++++ 1");
        System.out.println("methodSix +++++ 2");
//            if (1 == 1) {
//                throw new IllegalStateException("hello exception");
//            }
//            String temp = null;
//            System.out.println(temp.length());
        methodSeven();
        System.out.println("methodSix +++++ 3");
        System.out.println("methodSix +++++ 4");
//        } catch (Exception e) {
//            throw new IllegalStateException("from methodSix", e);
//        }
    }

    public static void methodSeven() {
//        try {
        System.out.println("methodSeven +++++ 1");
        System.out.println("methodSeven +++++ 2");
//            if (1 == 1) {
//                throw new IllegalStateException("hello exception");
//            }
//            String temp = null;
//            System.out.println(temp.length());
        methodEight();
        System.out.println("methodSeven +++++ 3");
        System.out.println("methodSeven +++++ 4");
//        } catch (Exception e) {
//            throw new IllegalStateException("from methodSix", e);
//        }
    }

    public static void methodEight() {
//        try {
            System.out.println("methodEight +++++ 1");
            System.out.println("methodEight +++++ 2");
//            if (1 == 1) {
//                throw new IllegalStateException("hello exception");
//            }
            String temp = null;
            System.out.println(temp.length());
            System.out.println("methodEight +++++ 3");
            System.out.println("methodEight +++++ 4");
//        } catch (Exception e) {
//            throw new IllegalStateException("from methodSix", e);
//        }
    }

    public static void main(String[] args) {
        System.out.println("main1");
        System.out.println("main2");
        System.out.println("main3");
        topOne();
        System.out.println("main4");
        System.out.println("main5");
    }

}
