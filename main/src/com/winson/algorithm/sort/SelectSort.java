package com.winson.algorithm.sort;

/**
 * @author winson
 * @date 2020/11/30
 * @desc 选择排序算法
 **/
public class SelectSort {

    public static int[] arr = {34, 52, 11, 56, 29, 40, 15, 42};

    public static void main(String[] args) {
        System.out.println("before select sort");
        SortUtil.printArr(arr);

        for (int i=0;i<arr.length;i++){
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        System.out.println("after select sort");
        SortUtil.printArr(arr);
    }

}
