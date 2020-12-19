package com.winson.algorithm.sort;

/**
 * @author winson
 * @date 2020/11/30
 * @desc 插入排序
 **/
public class InsertSort {

    public static int[] arr = {34, 52, 11, 56, 29, 40, 15, 42};

    public static void main(String[] args) {
        System.out.println("before insert sort");
        SortUtil.printArr(arr);

        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0) {
                boolean sentry = false;
                int sj = j - 1;
                if (arr[sj] > arr[j]) {
                    int temp = arr[sj];
                    arr[sj] = arr[j];
                    arr[j] = temp;
                    sentry = true;
                }
                if(!sentry){
                    break;
                }
                j--;
            }
        }


        System.out.println("before insert sort");
        SortUtil.printArr(arr);
    }

}
