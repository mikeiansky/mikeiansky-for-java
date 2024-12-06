package io.github.mikeiansky.java.base.jdk.exception;

/**
 * @author mike ian
 * @date 2024/10/28
 * @desc
 **/
public class ExceptionStackDemo {

    static class Ex1 extends Exception {

        public Ex1(String message) {
            super(message);
        }

        public Ex1(String message, Throwable cause) {
            super(message, cause);
        }

    }

    static class Ex2 extends Exception {

        public Ex2(String message) {
            super(message);
        }

        public Ex2(String message, Throwable cause) {
            super(message, cause);
        }

    }

    public static void one(String tag) throws Exception {
        System.out.println("one start " + tag);
        two("from one");
        System.out.println("one complete " + tag);
    }

    public static void two(String tag) throws Exception {
        System.out.println("two start " + tag);
        try {
            three("from two");
        } catch (Exception e) {
            throw new Ex2("exception from two", e);
        }
        System.out.println("two complete " + tag);
    }

    public static void three(String tag) throws Exception {
        System.out.println("three start " + tag);
        try {
            four("from three");
        } catch (Exception e) {
            throw new Ex1("from three exception", e);
        }
        System.out.println("three complete " + tag);
    }

    public static void four(String tag) throws Exception {
        System.out.println("four start " + tag);
        System.out.println("four doing ... ");
        if (1 == 1) {
            throw new Exception("raw exception from four");
        }
        System.out.println("four complete " + tag);
    }

    public static void main(String[] args) throws Exception {

        one("from main");

    }

}
