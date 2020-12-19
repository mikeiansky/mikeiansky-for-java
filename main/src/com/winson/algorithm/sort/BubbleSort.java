package com.winson.algorithm.sort;

/**
 * @author winson
 * @date 2020/11/27
 * @desc 冒泡排序算法
 **/
public class BubbleSort {

    public static int[] arr = {34, 52, 11, 56, 29, 40, 15, 42};

    public static void main(String[] args) {
        System.out.println("before bubble sort");
        SortUtil.printArr(arr);


        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }


        System.out.println("after bubble sort");
        SortUtil.printArr(arr);
    }


}
