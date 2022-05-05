package com.winson.throwable;

/**
 * @author winson
 * @date 2022/5/5
 **/
public class MyExceptionDemoV1 {

    public static void createException() {
        System.out.println("three");
        String tag = null;
//        tag.length();
        if (1 == 1) {
            throw new IllegalArgumentException("test exception message ");
        }
        System.out.println("four");
    }

    public static void main(String[] args) {
        System.out.println("one");
        try {
            createException();
        } catch (Exception e) {
//            new IllegalArgumentException(e);
            System.out.println();
            System.out.println("e:" + e);
            System.out.println("e.getMessage():" + e.getMessage());
            System.out.println("e.getCause():" + e.getCause());
            System.out.println("e.getStackTrace()[0]:" + e.getStackTrace()[0]);
            String complex = e + " [ "+e.getMessage()+" ] ";
            System.out.println("complex:"+complex);
        }
        System.out.println("two");

    }

}
