package com.winson.syntax.exception;

/**
 * @author winson
 * @date 2021/7/5
 **/
public class FinallyDemo {

    public static void main(String[] args) {
        boolean ae = true;
        try {
            if(ae){
                throw new IllegalArgumentException("ae test");
            }
        } catch (IllegalArgumentException e){
            e.printStackTrace();
            System.out.println("run catch at 1");
        } finally {
            System.out.println("after IllegalArgumentException exception");
        }

        boolean ao = true;
        try {
            if(ao){
                throw new NullPointerException("ae test");
            }
        } catch (IllegalArgumentException e){
            e.printStackTrace();
            System.out.println("run catch at 2");
        } finally {
            System.out.println("out of IllegalArgumentException exception");
        }

    }

}
