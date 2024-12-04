package com.winson.jdkapi.base;

/**
 * @author winson
 * @date 2021/10/16
 **/
public class UnsafeDemoV1 {

    public static boolean equal(Object o1, Object o2){
        if(o1 == null){
            return false;
        }
        if(o2 == null){
            return false;
        }
        return o1.equals(o2);
    }

    public static void main(String[] args) {

//        String str = "2201042038";
//        System.out.println(Integer.parseInt(str));
//        System.out.println(Integer.MAX_VALUE);

        Integer v1 = 12;
        int v2 = 12;
        System.out.println(equal(v1, v2));
        System.out.println(equal(v2, v1));

    }

}
