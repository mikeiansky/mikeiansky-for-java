package com.winson.temp;

/**
 * @author winson
 * @date 2022/2/21
 **/
public class ExceptionUtilDemo {

    public static void main(String[] args) {

        try {
//            String a = null;
//            System.out.println(a.length());
            if(1== 1){
                throw new IllegalArgumentException("winson errlr");
            }
        } catch (Exception e) {
            System.out.println("[ "+e.getClass() +" : " + e.getMessage()+" ]");
//            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());
//            System.out.println(e.getCause().getMessage());
//            System.out.println(e.getCause().getMessage());
        }


    }

}
