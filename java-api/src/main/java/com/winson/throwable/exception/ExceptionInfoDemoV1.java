package com.winson.throwable.exception;

/**
 * @author winson
 * @date 2022/5/17
 **/
public class ExceptionInfoDemoV1 {

    public static void main(String[] args) {

        nullPoint();
        customNullPoint();

    }

    public static void customNullPoint(){
        try {
            String hello = null;
            if(hello == null){
                throw new NullPointerException("hello must not null");
            }
        } catch (Exception e) {
            System.out.println("customNullPoint --> " + e);
            e.printStackTrace();
        }
    }

    public static void nullPoint(){
        try {
            String hello = null;
            hello.length();
        } catch (Exception e) {
            System.out.println("nullPoint --> " + e);
            e.printStackTrace();
        }
    }

}
