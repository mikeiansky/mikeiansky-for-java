package com.winson.syntax.exception;

/**
 * @author winson
 * @date 2021/5/18
 **/
public class TestException {

    public static void main(String[] args) {
//        System.out.println("test exception start ... ");
        int index = -1;
        int[] test = new int[]{1,2,3,4,5,6};
        try {
            System.out.println("this is exception body one line ... ");
            System.out.println(test[index]);
//            System.out.println("this is exception body two line ... ");
//            System.out.println("this is exception body three line ... ");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
//            System.out.println("exception catch");
        }
        finally {
            System.out.println("exception finally");
        }

//        System.out.println("test exception end ... ");

    }

}
