package com.winson.algorithm.sort.v2;

/**
 * @author mike ian
 * @date 2023/8/20
 * @desc 归并排序，非原地排序，稳定排序
 **/
public class MergeSortV2 {
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // 数组为空或只有一个元素时，无需排序
        }
        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, temp, left, mid); // 对左半部分进行归并排序
            mergeSort(arr, temp, mid + 1, right); // 对右半部分进行归并排序
            merge(arr, temp, left, mid, right); // 合并两个有序数组
        }
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        // 将左半部分复制到临时数组
        for (int i = left; i <= mid; i++) {
            temp[i] = arr[i];
        }

        // 将右半部分逆序复制到临时数组
        for (int j = right; j > mid; j--) {
            temp[mid + 1 + right - j] = arr[j];
        }

        int i = left; // 左半部分的起始索引
        int j = right; // 右半部分的起始索引

        for (int k = left; k <= right; k++) {
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j--;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 7, 6};
        mergeSort(arr);

        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

}
