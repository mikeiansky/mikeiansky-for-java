package com.winson.algorithm.sort;

/**
 * @author com.winson
 * @date 2020/11/27
 **/
public class SortUtil {

    public static void printArr(int[] arr){
        String result = getArrStr(arr);
        System.out.println(result);
    }

    public static <T> void printArr(T[] arr){
        String result = getArrStr(arr);
        System.out.println(result);
    }

    public static <T> String getArrStr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int t : arr) {
            sb.append(t);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public static <T> String getArrStr(T[] arr) {
        StringBuilder sb = new StringBuilder();
        for (T t : arr) {
            sb.append(t);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

}
