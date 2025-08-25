package com.winson.algorithm.sort.v5;

import java.util.Arrays;

public class MergeSortV5 {

    public static void main(String[] args) {

        int data[] = {26,11,56,16,30,29,99,64};
        System.out.println("before sort");
        System.out.println(Arrays.toString(data));

        mergeSort(data);

        System.out.println("after sort");
        System.out.println(Arrays.toString(data));
    }

    // 归并排序主方法
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // 如果数组为空或只有一个元素，无需排序
        }
        int[] temp = new int[arr.length]; // 用于合并的辅助数组
        mergeSort(arr, 0, arr.length - 1, temp); // 递归排序
    }

    // 递归排序方法
    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = left + (right - left) / 2; // 防止溢出，等同于 (left + right) / 2

            // 1. 分：递归排序左半部分
            mergeSort(arr, left, mid, temp);

            // 2. 分：递归排序右半部分
            mergeSort(arr, mid + 1, right, temp);

            // 3. 治：合并两个有序部分
            merge(arr, left, mid, right, temp);
        }
    }

    // 合并两个有序子数组 [left..mid] 和 [mid+1..right]
    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;       // 左子数组起始索引
        int j = mid + 1;    // 右子数组起始索引
        int k = left;       // 临时数组的索引

        // 1. 比较左右子数组的元素，按顺序放入 temp
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // 2. 如果左子数组还有剩余元素，全部拷贝到 temp
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // 3. 如果右子数组还有剩余元素，全部拷贝到 temp
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // 4. 将 temp 中排序好的数据拷贝回原数组 arr[left..right]
        for (k = left; k <= right; k++) {
            arr[k] = temp[k];
        }
    }

}
