package com.winson.throwable;

/**
 * @author winson
 * @date 2022/5/25
 **/
public class ThrowOnCatchDemo {

    public static void throwOnCatch(){
        String flag = "hello";
        try {
            System.out.println("run normal code");
            String tag = null;
            System.out.println(tag.length());
        } catch (Exception e) {
            System.out.println("run catch code");
            String cs = null;
            System.out.println(cs.length());
        } finally {
            System.out.println("run finally code");
        }
    }

    public static void throwButNoCatch(){
        try {
            throw new IllegalArgumentException("test exception");
        } catch (NullPointerException e){
            System.out.println("NullPointerException ");
        } finally {
            System.out.println("run finally ... ");
        }
    }

    public static void main(String[] args) {
//        throwOnCatch();
        throwButNoCatch();
    }

}
