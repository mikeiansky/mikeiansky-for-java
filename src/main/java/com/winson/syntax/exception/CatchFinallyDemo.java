package com.winson.syntax.exception;

/**
 * @author winson
 * @date 2021/7/9
 **/
public class CatchFinallyDemo {

    public static int getFinally(){
        int x = 0;
        try {
            x = 1;
            return x;
        }catch (Exception e){
            e.printStackTrace();
            x= 2;
            return x;
        } finally {
            x = 3;
        }
    }

    public static void main(String[] args) {
        System.out.println(getFinally());
    }

}
