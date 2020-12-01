package com.winson.algorithm.sort;

/**
 * @author winson
 * @date 2020/11/30
 * @desc 快速排序
 **/
public class QuickSort {

    public static int[] arr = {34, 52, 11, 56, 29, 40, 15, 42};

    public static void main(String[] args) {
        System.out.println("before quick sort");
        SortUtil.printArr(arr);

        quickSort(arr, 0, arr.length - 1);
//        quickSort(arr, 0, arr.length - 1, 3);
//        GoodQuickSort.quickSort(arr, arr.length);

        System.out.println("after quick sort");
        SortUtil.printArr(arr);
    }

    public static void quickSort(int[] qa, int p, int r) {
        int length = r - p;
        if (length <= 1) {
            return;
        }
        int split = p;
        int pivot = r;
        int pivotValue = qa[pivot];
        for (int i = p; i <= r; i++) {
            if (qa[i] < pivotValue) {
                if (i != split) {
                    int temp = qa[split];
                    qa[split] = qa[i];
                    qa[i] = temp;
                }
                split++;
            }
        }

        int temp = qa[pivot];
        qa[pivot] = qa[split];
        qa[split] = temp;
        pivot = split;


        quickSort(qa, p, pivot - 1);
        quickSort(qa, pivot + 1, r);
    }

}
