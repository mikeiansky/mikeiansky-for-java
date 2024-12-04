package com.winson.jdkapi.exception;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author winson
 * @date 2021/9/13
 **/
public class ExceptionDemoV1 {

    public static class WinsonError extends Error{

    }

    public static class WinsonRuntimeException extends RuntimeException{

    }

    public static class WinsonCheckException extends Exception{

    }

    public static void testError() throws WinsonError{
        System.out.println("test error");
        throw new WinsonError();
    }

    public static void testRuntimeException() throws WinsonRuntimeException{
        throw new WinsonRuntimeException();
    }

    public static void testCheckException() throws WinsonCheckException{
        throw new WinsonCheckException();
    }

    public static void main(String[] args) {

        try {
            testError();
        }catch (Error e){
            e.printStackTrace();
        }

        try {
            testRuntimeException();
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            testCheckException();
        } catch (WinsonCheckException e) {
            e.printStackTrace();
        }

        List<String> list = Arrays.asList("1","2","3");
        System.out.println(list);
        list.add("4");
        System.out.println(list);

        System.out.println("app end ... ");
    }

}
